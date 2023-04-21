package kk.example.jetpackcomposesturacturalapp.presentation.common_screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgress(
    canvasSize: Dp =150.dp,
    indicatorValue:Int=0,
    maxIndicatorValue:Int=100,
    backgroundIndicatorColor: Color =MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth:Float=60f,
    foregroundIndicatorColor:Color= MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth:Float=60f,
    indicatorStrokeCap: StrokeCap= StrokeCap.Round,
    bigTextFontSize: TextUnit =MaterialTheme.typography.bodySmall.fontSize,
    bigTextColor: Color=MaterialTheme.colorScheme.onSurface,
    bigTextSuffix: String="Item",
    smallText: String="Remaining",
    smallTextFontSize: TextUnit=MaterialTheme.typography.bodySmall.fontSize,
    smallTextColor: Color=MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

){

    var allowedIndicatorValue by remember{ mutableStateOf(maxIndicatorValue) }

    allowedIndicatorValue=if (indicatorValue<=maxIndicatorValue){
        indicatorValue
    }else{
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember{ mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue ){
        animatedIndicatorValue=allowedIndicatorValue.toFloat()
    }
    val percentage=(animatedIndicatorValue/maxIndicatorValue)*100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4*percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue==0){
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        }else{
            bigTextColor
        },
        animationSpec = tween(1000)
    )



    Column(modifier = Modifier
        .padding(4.dp)
        .size(canvasSize)
        .drawBehind {
            val componentSize = size / 1.25f
            backgroundIndicator(
                componentSize = componentSize,
                indicatorColor = backgroundIndicatorColor,
                indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
                indicatorStrokeCap = indicatorStrokeCap
            )
            foregroundIndicator(
                sweepAngle = sweepAngle,
                componentSize = componentSize,
                indicatorColor = foregroundIndicatorColor,
                indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                indicatorStrokeCap = indicatorStrokeCap
            )
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(
            bigText = allowedIndicatorValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize =smallTextFontSize
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: androidx.compose.ui.geometry.Size,
    indicatorColor :Color,
    indicatorStrokeWidth:Float,
    indicatorStrokeCap: StrokeCap
){
    drawArc(
        size=componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap= indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width-componentSize.width)/2f,
            y =  (size.width-componentSize.width)/2f
        )
    )

}


fun DrawScope.foregroundIndicator(
    sweepAngle:Float,
    componentSize: androidx.compose.ui.geometry.Size,
    indicatorColor :Color,
    indicatorStrokeWidth:Float,
    indicatorStrokeCap: StrokeCap
){
    drawArc(
        size=componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap= indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width-componentSize.width)/2f,
            y =  (size.width-componentSize.width)/2f
        )
    )

}

@Composable
fun EmbeddedElements(
    bigText:Int,
    bigTextFontSize:TextUnit,
    bigTextColor:Color,
    bigTextSuffix:String,
    smallText:String,
    smallTextColor:Color,
    smallTextFontSize:TextUnit
){
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )

    Text(
        text = "$bigText ${bigTextSuffix.take(4)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun CircularProgressPreview() {
    CircularProgress(indicatorValue = 69)
}