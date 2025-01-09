package com.easternkite.krogress.components.indicators.gridIndicator

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun GridPulsatingDot(
    color: Color = Color.White,
    ballDiameter: Float = 40f,
    verticalSpace: Float = 20f,
    horizontalSpace: Float = 20f,
    minAlpha: Float = 0.5f,
    maxAlpha: Float = 1f,
    animationDuration: Int = 600
) {


    val rowCount: Int = 3
    val columnCount: Int = 3
    val totalBallsCount = columnCount * rowCount

    val alphas: List<Float> = (0 until totalBallsCount).map { index ->
        var alpha by remember { mutableStateOf(maxAlpha) }

        LaunchedEffect(key1 = Unit) {

            delay(200L * index)

            animate(
                initialValue = minAlpha,
                targetValue = maxAlpha,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse,
                ),
            ) { value, _ ->
                alpha = value
            }
        }
        alpha
    }

    Canvas(modifier = Modifier) {
        val center = Offset(size.width / 2, size.height / 2)
        for (row in 0 until rowCount) {
            for (col in 0 until columnCount) {

                val xOffset = ballDiameter + horizontalSpace
                val yOffset = ballDiameter + verticalSpace

                drawCircle(
                    color = color,
                    radius = (ballDiameter / 2) * alphas[row * columnCount + col],
                    center = Offset(
                        x = when {
                            col < columnCount / 2 -> -(center.x + xOffset)
                            col == columnCount / 2 -> center.x
                            else -> center.x + xOffset
                        },
                        y =
                        when {
                            row < rowCount / 2 -> -(center.y + yOffset)
                            row == rowCount / 2 -> center.y
                            else -> center.y + yOffset
                        },
                    ),
                    alpha = alphas[row * columnCount + col]
                )
            }
        }
    }
}