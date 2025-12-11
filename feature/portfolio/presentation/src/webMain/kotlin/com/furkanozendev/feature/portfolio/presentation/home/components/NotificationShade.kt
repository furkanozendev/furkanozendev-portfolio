package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NotificationShade(
    progress: Float,
    modifier: Modifier = Modifier
) {
    if (progress == 0f) return

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, end = 16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth(if (progress < 0.2f) 0.9f else 1f)
                .height(500.dp * progress)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF1E1E1E).copy(alpha = 0.95f))
                .padding(16.dp)
        ) {
            if (progress > 0.3f) {
                Text("Quick Settings", style = MaterialTheme.typography.titleMedium, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(Modifier.size(50.dp).background(Color.Gray, RoundedCornerShape(12.dp)))
                    Box(Modifier.size(50.dp).background(Color.Gray, RoundedCornerShape(12.dp)))
                }
            }
        }
    }
}