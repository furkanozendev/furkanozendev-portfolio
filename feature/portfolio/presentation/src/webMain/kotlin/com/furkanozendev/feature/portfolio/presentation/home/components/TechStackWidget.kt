package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TechStackWidget(modifier: Modifier = Modifier) {
    BentoCard(
        modifier = modifier,
        title = "System Capabilities",
        icon = Icons.Rounded.Code
    ) {
        Text("LEFT BOTTOM\nTech Stack (Grid)", color = Color.White)
    }
}