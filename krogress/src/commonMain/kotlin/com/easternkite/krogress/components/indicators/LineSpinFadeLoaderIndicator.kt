/*
 * Copyright 2025 easternkite
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easternkite.krogress.components.indicators

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
import androidx.compose.ui.graphics.StrokeCap
import com.easternkite.krogress.enums.LinearAnimationType
import com.easternkite.krogress.extension.toRadians
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LineSpinFadeLoaderIndicator(
    color: Color = Color.White,
    rectCount: Int = 8,
    linearAnimationType: LinearAnimationType = LinearAnimationType.CIRCULAR,
    penThickness: Float = 25f,
    radius: Float = 55f,
    elementHeight: Float = 20f,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 1.0f
) {

    val angleStep = 360f / rectCount
    val outerRadius = radius + elementHeight
    val innerRadius = radius


// ------------------------ scale animation ---------------------
    val alphas = (1..rectCount).map { index ->
        var alpha: Float by remember { mutableStateOf(minAlpha) }
        LaunchedEffect(key1 = Unit) {

            when (linearAnimationType) {
                LinearAnimationType.CIRCULAR -> {
                    delay(linearAnimationType.circleDelay * index)
                }

                LinearAnimationType.SKIP_AND_REPEAT -> {
                    delay(linearAnimationType.circleDelay * index) // The constant value, here 250L, must be the same animation duration for this pattern to run
                }
            }

            animate(
                initialValue = minAlpha,
                targetValue = maxAlpha,
                animationSpec = infiniteRepeatable(
                    animation = when (linearAnimationType) {
                        LinearAnimationType.CIRCULAR -> {
                            tween(durationMillis = linearAnimationType.animDuration)
                        }

                        LinearAnimationType.SKIP_AND_REPEAT -> {
                            tween(durationMillis = linearAnimationType.animDuration)
                        }
                    },
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> alpha = value }
        }

        alpha
    }


// ----------------------------- UI --------------------------

    Canvas(modifier = Modifier) {

        val center = Offset(size.width / 2, size.height / 2)

        for (index in 0 until rectCount) {

            val angle = index * angleStep

            val startX =
                center.x + innerRadius * cos(angle.toRadians()).toFloat()
            val startY =
                center.y + innerRadius * sin(angle.toRadians()).toFloat()

            val endX = center.x + outerRadius * cos(angle.toRadians()).toFloat()
            val endY = center.y + outerRadius * sin(angle.toRadians()).toFloat()

            drawLine(
                color = color,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = penThickness * alphas[index],
                alpha = alphas[index],
                cap = StrokeCap.Round,
            )
        }
    }
}