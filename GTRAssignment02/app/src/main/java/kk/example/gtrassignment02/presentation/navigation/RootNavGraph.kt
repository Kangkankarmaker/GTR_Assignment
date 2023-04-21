package kk.example.jetpackcomposesturacturalapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kk.example.jetpackcomposesturacturalapp.presentation.common_screens.LoadingAnimation
import kk.example.jetpackcomposesturacturalapp.presentation.screens.DashBoardScreen
import kk.example.jetpackcomposesturacturalapp.presentation.screens.OnBoardingScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination =startDestination
    ) {


        composable(route = Screen.Loading.route) {
            LoadingAnimation(modifier = Modifier.padding(60.dp) )
        }

        composable(route = Screen.Welcome.route) {
            OnBoardingScreen(navController = navController)
        }

        composable(route = Screen.DashBoard.route) {
            DashBoardScreen()
        }


    }
}


