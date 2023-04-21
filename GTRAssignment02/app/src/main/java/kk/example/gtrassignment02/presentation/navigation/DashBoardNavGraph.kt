package kk.example.jetpackcomposesturacturalapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kk.example.jetpackcomposesturacturalapp.presentation.BottomBarScreen
import kk.example.jetpackcomposesturacturalapp.presentation.screens.HomeScreen
import kk.example.jetpackcomposesturacturalapp.presentation.screens.ProductScreen
import kk.example.jetpackcomposesturacturalapp.presentation.screens.PromotionalScreen

@Composable
fun DashBoardNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.DASHBOARD,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Cart.route) {
            ProductScreen()
        }
        composable(route = BottomBarScreen.Favourite.route) {
            PromotionalScreen()
        }

        composable(route = BottomBarScreen.Me.route) {
            ProductScreen()
        }


    }
}

