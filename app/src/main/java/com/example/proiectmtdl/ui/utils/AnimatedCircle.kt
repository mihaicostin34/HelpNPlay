package com.example.proiectmtdl.ui.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private const val DividerLengthInDegrees = 1.8f

@Composable
fun AnimatedCircle(
    proportions: List<Float> = listOf(200000f, 500000f, 290000000f),
    colors: List<Color> = listOf(Color.Cyan, Color.Red, Color.Yellow),
    modifier: Modifier = Modifier
) {
    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val stroke = with(LocalDensity.current) { Stroke(5.dp.toPx()) }
    val transition = updateTransition(currentState)
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            360f
        }
    }
    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        var startAngle = shift - 90f
        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = colors[index],
                startAngle = startAngle + DividerLengthInDegrees / 2,
                sweepAngle = sweep - DividerLengthInDegrees,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            startAngle += sweep
        }
    }
}
private enum class AnimatedCircleProgress { START, END }

@Composable
fun ProgressCircle(
    targetValue: Float = 80f,
    radius : Float = 400f,
    level: Int = 7,
    circleColor : Color,
    modifier: Modifier = Modifier
) {
    val animateFloat = remember{ Animatable(0f) }
    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = targetValue,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
    }
    Row(
    ) {
        val textMeasurer = rememberTextMeasurer()
        val style = TextStyle(
            fontSize =60.sp,
            color = Color.Black
        )
        val textLayoutResult = remember(level.toString()){
            textMeasurer.measure(level.toString(), style)
        }
        Canvas(
            modifier = Modifier
                .height((180).dp)
                .fillMaxWidth()
        )
        {
            val width = size.width
            val height = size.height

            drawArc(
                color = circleColor,
                startAngle = -90f,
                sweepAngle = 360f * animateFloat.value,
                useCenter = false,
                size = Size(radius, radius),
                topLeft = Offset(
                    (width / 2) - (radius / 2),
                    height / 2 - (radius / 2)
                ),
                style = Stroke(5.0f)
            )
            drawText(
                textMeasurer,
                text = "${level}",
                style = style,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width/2,
                    y = center.y - textLayoutResult.size.height/2
                )
            )
        }
    }
}