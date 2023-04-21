package kk.example.jetpackcomposesturacturalapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kk.example.gtrassignment02.R
import kk.example.jetpackcomposesturacturalapp.presentation.navigation.DashBoardNavGraph
import kk.example.jetpackcomposesturacturalapp.presentation.BottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        DashBoardNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Favourite,
        BottomBarScreen.Me
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            modifier = Modifier
                .graphicsLayer {
                    shape= RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                    clip=true
                },
            backgroundColor = colorResource(id = R.color.white2)
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                color = if (selected) colorResource(id = R.color.green) else Color.Black.copy(alpha = 0.5f) ,
                fontSize = 12.sp
            )
        },
        icon = {

            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (selected)colorResource(id = R.color.green) else Color.Black.copy(alpha = 0.5f) ,
            )

        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}