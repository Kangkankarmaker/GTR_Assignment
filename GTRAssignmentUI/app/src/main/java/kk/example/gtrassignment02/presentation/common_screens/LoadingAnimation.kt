package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation(
    modifier: Modifier,
    circleSize:Dp=25.dp,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    spaceBetween:Dp=10.dp,
    travelDistance:Dp=20.dp
) {
    val circles= listOf(
        remember { androidx.compose.animation.core.Animatable(initialValue = 0f) },
        remember { androidx.compose.animation.core.Animatable(initialValue = 0f) },
        remember { androidx.compose.animation.core.Animatable(initialValue = 0f) }
    )


    Dialog(onDismissRequest = { }) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            circles.forEachIndexed { index,animatable->
                LaunchedEffect(key1 = animatable){
                    delay(index*100L)
                    animatable.animateTo(
                        1f,
                        animationSpec = infiniteRepeatable(
                            animation = keyframes {
                                durationMillis=1200
                                0.0f at 0 with  LinearOutSlowInEasing
                                1.0f at 300 with  LinearOutSlowInEasing
                                0.0f at 600 with LinearOutSlowInEasing
                                0.0f at 1200 with LinearOutSlowInEasing
                            },
                            repeatMode = RepeatMode.Restart
                        )
                    )
                }
            }

            val circlesValues=circles.map { it.value }
            val distance= with(LocalDensity.current){travelDistance.toPx()}
            val lastCircle=circlesValues.size-1

            Row(modifier = modifier) {
                circlesValues.forEachIndexed{index, value ->
                    Box(
                        modifier = Modifier
                            .size(circleSize)
                            .graphicsLayer {
                                translationY = -value * distance
                            }
                            .background(
                                color = circleColor,
                                shape = CircleShape
                            )
                    )
                    if (index !=lastCircle){
                        Spacer(modifier = Modifier.width(spaceBetween))
                    }
                }
            }

        }
    }
}

@Composable
fun LoadingAnimation2(
    circleColor: Color = Color.Magenta,
    animationDelay: Int = 10
) {

    // 3 circles
    val circles = listOf(
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            // Use coroutine delay to sync animations
            // divide the animation delay by number of circles
            delay(timeMillis = (animationDelay / 3L) * (index + 1))

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // outer circle
    Box(
        modifier = Modifier
            .size(size = 200.dp)
            .background(color = Color.Transparent)
    ) {
        // animating circles
        circles.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .scale(scale = animatable.value)
                    .size(size = 200.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            ) {
            }
        }
    }
}