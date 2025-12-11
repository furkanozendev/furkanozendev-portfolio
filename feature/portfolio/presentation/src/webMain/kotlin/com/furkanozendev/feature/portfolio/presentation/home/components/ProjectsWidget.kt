package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProjectsWidget(modifier: Modifier = Modifier) {
    BentoCard(
        modifier = modifier,
        title = "Active Deployments",
        icon = Icons.Rounded.RocketLaunch
    ) {
        Text("RIGHT BOTTOM\nProjects Banner", color = Color.White)
    }
}