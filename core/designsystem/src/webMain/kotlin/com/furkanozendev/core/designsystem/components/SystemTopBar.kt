package com.furkanozendev.core.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Terminal
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.delay
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun SystemTopBar(
    modifier: Modifier = Modifier,
    onExpandShade: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(HomeColors.SystemTopBarBackground)
            .clickable(onClick = onExpandShade)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SystemTime()
            Spacer(modifier = Modifier.width(12.dp))
            StatusIcon(Icons.Rounded.Terminal, contentDescription = "Terminal Active")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            StatusIcon(Icons.Rounded.Wifi, contentDescription = "Wifi Connected")
            Spacer(modifier = Modifier.width(8.dp))
            BatteryIcon()
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun SystemTime() {
    var timeString by remember { mutableStateOf("12:00") }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val hour = now.hour.toString().padStart(2, '0')
            val minute = now.minute.toString().padStart(2, '0')
            timeString = "$hour:$minute"
            delay(1000L * 30)
        }
    }

    Text(
        text = timeString,
        style = MaterialTheme.typography.labelMedium.copy(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = HomeColors.SystemTopMain
        )
    )
}

@Composable
private fun StatusIcon(icon: ImageVector, contentDescription: String) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = HomeColors.SystemTopMain,
        modifier = Modifier.size(14.dp)
    )
}

@Composable
private fun BatteryIcon(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(width = 20.dp, height = 10.dp)) {
        val strokeWidth = 1.dp.toPx()
        val color = HomeColors.SystemTopMain

        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(size.width - 2.dp.toPx(), size.height),
            style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth),
            cornerRadius = CornerRadius(1.dp.toPx())
        )

        drawRect(
            color = color,
            topLeft = Offset(size.width - 1.5.dp.toPx(), size.height * 0.3f),
            size = Size(1.5.dp.toPx(), size.height * 0.4f)
        )

        val fillWidth = (size.width - 4.dp.toPx()) * 0.4f
        drawRect(
            color = HomeColors.SystemTopMain,
            topLeft = Offset(1.5.dp.toPx(), 1.5.dp.toPx()),
            size = Size(fillWidth, size.height - 3.dp.toPx())
        )
    }
}