package kk.example.jetpackcomposesturacturalapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kk.example.gtrassignment02.R

sealed class OnBoardingPage(
    @DrawableRes
    val image:Int,
    val title:String,
    val description:String,
    val backgroundColor:Color,
    val mainColor:Color,
){
    object First:OnBoardingPage(
        image = R.drawable.slider03,
        title = "Welcome to\nUBazar Application",
        description = "",
        backgroundColor = Color(0xFF0189C5),
        mainColor = Color(0xFF00B5EA)
    )

    object Second:OnBoardingPage(
        image = R.drawable.slider01,
        title = "Get\nFast Delivery Services",
        description = "",
        backgroundColor = Color(0xFFAA7F07),
        mainColor = Color(0xFFE4AF19)
    )

    object Third:OnBoardingPage(
        image = R.drawable.slider02,
        title = "Best Quality\nGrocery Door to Door",
        description = "",
        backgroundColor = Color(0xFF547E3F),
        mainColor = Color(0xFF96E172)
    )
}
