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
import com.easternkite.krogress.enums.LinearAnimationType
import com.easternkite.krogress.extension.toRadians
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BallSpinFadeLoaderIndicator(
    color: Color = Color.White,
    linearAnimationType: LinearAnimationType = LinearAnimationType.CIRCULAR,
    radius: Float = 70f,
    ballCount: Int = 8,
    ballRadius: Float = 12f
) {

    val angleStep = 360f / ballCount

// ------------------------ scale animation ---------------------
    val animationValues = (1..ballCount).map { index ->
        var animatedValue: Float by remember { mutableStateOf(0f) }
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
                initialValue = 0.2f,
                targetValue = 1f,
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
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }



// ----------------------------- UI --------------------------
    Canvas(
        modifier = Modifier
    ) {
        val center = Offset(size.width / 2, size.height / 2)
        for (index in 0 until ballCount) {
            val angle = index * angleStep
            val x = center.x + radius * cos(angle.toRadians()).toFloat()
            val y = center.y + radius * sin(angle.toRadians()).toFloat()
            drawCircle(
                color = color,
                radius = ballRadius * animationValues[index], // Apply the scale
                center = Offset(x, y)
            )
        }
    }
}