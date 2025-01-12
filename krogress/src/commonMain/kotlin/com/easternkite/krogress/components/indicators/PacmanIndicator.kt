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

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PacmanIndicator(
    color: Color = Color.White,
    ballDiameter: Float = 40f,
    canvasSize: Dp = 40.dp,
    animationDuration: Int = 500
) {

    val lipStart = 0f
    val lipEnd = 45f

    val positionAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = ballDiameter,
        targetValue = -ballDiameter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val lipAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = lipStart,
        targetValue = lipEnd,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration / 2, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.size(canvasSize)) {
        drawArc(
            color = color,
            startAngle = lipAnimation,
            sweepAngle = 360 - lipAnimation.times(2),
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height),
            useCenter = true
        )
        drawCircle(
            color = color,
            radius = ballDiameter / 2,
            center = Offset(
                x = size.width + positionAnimation,
                y = size.height / 2
            )
        )
    }
}
