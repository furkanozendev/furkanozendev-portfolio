package com.furkanozendev.core.designsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.furkanozendev.core.designsystem.colors.LocalHomeColors

@Composable
fun Modifier.glassGradientBackground(): Modifier {
    val colors = LocalHomeColors.current

    return this.then(
        Modifier.drawBehind {
            drawRect(color = colors.surfaceBase)

            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(colors.glowBlue, Color.Transparent),
                    center = Offset(0f, 0f),
                    radius = size.maxDimension * 0.9f
                )
            )

            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(colors.glowOrange, Color.Transparent),
                    center = Offset(size.width, size.height),
                    radius = size.maxDimension * 0.9f
                )
            )
        }
    )
}
