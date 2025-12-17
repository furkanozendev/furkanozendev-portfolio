package com.furkanozendev.feature.portfolio.presentation.home.components.experience

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun TimelineRail(isLast: Boolean, colors: HomeColors) {
    Column(
        modifier = Modifier
            .width(18.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(10.dp)
                .background(colors.timelineDot, CircleShape)
        )
        if (!isLast) {
            Spacer(Modifier.height(6.dp))
            Box(
                Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(colors.timelineRail)
            )
        }
    }
}
