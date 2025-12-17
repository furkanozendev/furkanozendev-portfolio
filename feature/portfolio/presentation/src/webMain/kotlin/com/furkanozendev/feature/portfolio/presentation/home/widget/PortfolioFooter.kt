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
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources

@Composable
fun PortfolioFooter(
    modifier: Modifier = Modifier,
    textMuted: Color = Color(0xFF9A9A9A)
) {
    val res = LocalStringResources.current
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = res["footer_tagline"],
            style = MaterialTheme.typography.bodySmall.copy(
                color = textMuted,
                textAlign = TextAlign.Center
            )
        )

        Text(
            text = res["footer_copyright"],
            style = MaterialTheme.typography.labelSmall.copy(
                color = textMuted
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FooterLink(res["github_title"]) {
                uriHandler.openUri(res["github_url"])
            }
            FooterLink(res["linkedin_title"]) {
                uriHandler.openUri(res["linkedin_url"])
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