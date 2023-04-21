package kk.example.jetpackcomposesturacturalapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route :String,
    val title :String,
    val icon :ImageVector
){
    object Home:BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Cart:BottomBarScreen(
        route = "Cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object Favourite:BottomBarScreen(
        route = "Favourite",
        title = "Favourite",
        icon = Icons.Default.Favorite
    )

    object Me:BottomBarScreen(
        route = "Me",
        title = "Me",
        icon = Icons.Default.Person
    )
}
