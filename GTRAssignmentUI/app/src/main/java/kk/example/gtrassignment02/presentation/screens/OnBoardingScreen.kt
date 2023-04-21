package kk.example.jetpackcomposesturacturalapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import kk.example.gtrassignment02.R
import kk.example.jetpackcomposesturacturalapp.presentation.OnBoardingPage
import kk.example.jetpackcomposesturacturalapp.presentation.navigation.Screen
import kk.example.jetpackcomposesturacturalapp.presentation.viewModel.OnBoardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            HorizontalPager(
                modifier = Modifier,
                count = 3,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
            HorizontalPagerIndicator(
                modifier = Modifier,
                pagerState = pagerState
            )
        }


        Box(
            modifier = Modifier.align(Alignment.BottomStart),
        ) {
            AnimatedVisibility(
                visible = pagerState.currentPage != 2,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                TextButton(onClick = {
                        if (pagerState.currentPage == 0) {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 2)
                                pagerState.scrollBy(100f)
                            }
                        } else if (pagerState.currentPage == 1) {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                                pagerState.scrollBy(100f)
                            }
                        }

                    }) {
                    Text(
                        text = "Skip",
                        color = Color.Black.copy(alpha = 0.8f)
                    )
                }

            }
        }

        Box(
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            AnimatedVisibility(
                visible = pagerState.currentPage != 2,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                OutlinedButton(
                    modifier = Modifier.size(65.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = pages[pagerState.currentPage].mainColor),
                    border = BorderStroke(
                        width = 14.dp,
                        color = pages[pagerState.currentPage].mainColor
                    ),
                    shape = RoundedCornerShape(50), // = 50% percent

                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                            pagerState.scrollBy(100f)
                        }
                    }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.navigate_next),
                        contentDescription = "",
                        tint = pages[pagerState.currentPage].mainColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        FinishButton(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter),
            pagerStare = pagerState
        ) {
            onBoardingViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.DashBoard.route)
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.title,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.8f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription =onBoardingPage.title
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerStare: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerStare.currentPage == 2
        ) {
            ElevatedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.onBackground
                ),
                shape = RoundedCornerShape(20), // = 50% percent
            ) {

                Text(text = "Let`s Start..")
                Image(
                    alignment=Alignment.CenterEnd,
                    painter = painterResource(id = R.drawable.baseline_home),
                    contentDescription = "Home"
                )
            }
        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4_XL, showSystemUi = true)
fun PagerScreenPreview() {
    PagerScreen(onBoardingPage = OnBoardingPage.Third)
}