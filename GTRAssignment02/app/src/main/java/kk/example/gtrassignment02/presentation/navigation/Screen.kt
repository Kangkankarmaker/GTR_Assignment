package kk.example.jetpackcomposesturacturalapp.presentation.navigation

sealed class Screen(val route:String){
    object Welcome: Screen(route = "welcome_screen")
    object DashBoard: Screen(route = "dashboard_screen")



    object Loading: Screen(route = "loading_screen")

}


object Graph {
    const val ROOT = "root_graph"
    const val DASHBOARD = "dashboard_graph"

}
