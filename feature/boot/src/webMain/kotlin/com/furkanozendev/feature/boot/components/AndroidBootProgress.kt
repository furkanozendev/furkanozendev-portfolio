package com.furkanozendev.feature.boot.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp

@Composable
fun AndroidBootProgress(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val targetProgress = (progress / 100f).coerceIn(0f, 1f)

    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = 3000,
            easing = FastOutSlowInEasing
        ),
        label = "ProgressAnimation"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
    ) {
        val cornerRadiusValue = size.height / 2
        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    cornerRadius = CornerRadius(cornerRadiusValue, cornerRadiusValue)
                )
            )
        }

        drawPath(
            path = path,
            color = BootColors.Track
        )

        clipPath(path) {
            val progressWidth = size.width * animatedProgress

            drawRect(
                color = BootColors.Progress,
                size = Size(width = progressWidth, height = size.height)
            )
        }
    }
}
