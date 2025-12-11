package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timeline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExperienceWidget(modifier: Modifier = Modifier) {
    BentoCard(
        modifier = modifier,
        title = "Runtime Logs (Experience)",
        icon = Icons.Rounded.Timeline
    ) {
        Text("RIGHT TOP\nExperience Timeline", color = Color.White)
    }
}