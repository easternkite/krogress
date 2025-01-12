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
package com.easternkite.krogress.components.indicators.shape_unveil_indicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.easternkite.krogress.enums.ShapeType

@Composable
fun ShapeUnveilIndicator(
    modifier: Modifier = Modifier,
    shapeType: ShapeType = ShapeType.TRIANGLE,
    color: Color = Color.White,
    canvasSize: Float = 200f,
    circleDiameter: Float = canvasSize / 4,
    circleCounts: Int = 6,  // currently just available for ShapeType.CIRCLE
    animationDuration: Int = 3000
) {
    when (shapeType) {
        ShapeType.TRIANGLE -> TriangleShapeIndicator(
            modifier,
            color,
            canvasSize,
            circleDiameter,
            animationDuration
        )

        ShapeType.CIRCLE -> CircleShapeIndicator(
            modifier,
            color,
            canvasSize,
            circleDiameter,
            circleCounts,
            animationDuration
        )
    }
}