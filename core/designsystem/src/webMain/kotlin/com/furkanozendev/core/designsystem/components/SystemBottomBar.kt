package com.furkanozendev.core.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
fun SystemBottomBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onHomeClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(HomeColors.SystemBottomBarBackground),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavButton(onClick = onBackClicked) {
            val path = Path().apply {
                moveTo(size.width * 0.7f, size.height * 0.25f)
                lineTo(size.width * 0.3f, size.height * 0.5f)
                lineTo(size.width * 0.7f, size.height * 0.75f)
                close()
            }
            drawPath(path, color = HomeColors.SystemBottomMain)
        }

        NavButton(onClick = onHomeClicked) {
            drawCircle(
                color = HomeColors.SystemBottomMain,
                radius = size.minDimension * 0.35f,
                style = Stroke(width = 2.dp.toPx())
            )
        }

        NavButton(onClick = { /* No-op or add callback later */ }) {
            val padding = size.width * 0.25f
            drawRoundRect(
                color = HomeColors.SystemBottomMain,
                topLeft = Offset(padding, padding),
                size = Size(size.width - padding * 2, size.height - padding * 2),
                cornerRadius = CornerRadius(4.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}

@Composable
private fun RowScope.NavButton(
    onClick: () -> Unit,
    drawContent: DrawScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = HomeColors.SystemBottomIndicator)
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(24.dp)) {
            drawContent()
        }
    }
}