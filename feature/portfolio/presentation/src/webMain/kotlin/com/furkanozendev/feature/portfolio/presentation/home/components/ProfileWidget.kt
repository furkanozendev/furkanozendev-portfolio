package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileWidget(modifier: Modifier = Modifier) {
    BentoCard(
        modifier = modifier,
        title = "Profile & Bio",
        icon = Icons.Rounded.Person
    ) {
        Text("LEFT TOP\nProfile & Bio", color = Color.White)
    }
}