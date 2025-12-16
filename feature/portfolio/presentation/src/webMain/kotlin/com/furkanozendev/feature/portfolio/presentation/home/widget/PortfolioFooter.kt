package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PortfolioFooter(
    modifier: Modifier = Modifier,
    textMuted: Color = Color(0xFF9A9A9A)
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Curated work focused on architecture, UI systems, and developer tooling.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = textMuted,
                textAlign = TextAlign.Center
            )
        )

        Text(
            text = "© Copyright 2025. Made by furkanozendev",
            style = MaterialTheme.typography.labelSmall.copy(
                color = textMuted
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FooterLink("GitHub") {
                uriHandler.openUri("https://github.com/furkanozendev")
            }
            FooterLink("LinkedIn") {
                uriHandler.openUri("https://linkedin.com/in/furkanozendev")
            }
        }
    }
}

@Composable
private fun FooterLink(
    label: String,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier.clickable(onClick = onClick),
        style = MaterialTheme.typography.labelSmall.copy(
            color = Color(0xFF8BE9FD),
            fontWeight = FontWeight.Medium
        )
    )
}