package com.furkanozendev.core.designsystem.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.furkanozendev.core.designsystem.colors.HomeColors.BaseBackground
import com.furkanozendev.core.designsystem.colors.HomeColors.BlueGlow
import com.furkanozendev.core.designsystem.colors.HomeColors.OrangeGlow

fun Modifier.glassGradientBackground(): Modifier = this.then(
    Modifier.drawBehind {
        drawRect(color = BaseBackground)

        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(BlueGlow, Color.Transparent),
                center = Offset(0f, 0f),
                radius = size.maxDimension * 0.9f
            )
        )

        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(OrangeGlow, Color.Transparent),
                center = Offset(size.width, size.height),
                radius = size.maxDimension * 0.9f
            )
        )
    }
)
