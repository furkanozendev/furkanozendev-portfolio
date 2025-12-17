package com.furkanozendev.feature.portfolio.presentation.home.widget.footer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PortfolioFooter(
    modifier: Modifier = Modifier,
    viewModel: PortfolioFooterViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val colors = LocalHomeColors.current
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = uiState.tagline,
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textMuted,
                textAlign = TextAlign.Center
            )
        )

        Text(
            text = uiState.copyright,
            style = MaterialTheme.typography.labelSmall.copy(
                color = colors.textMuted
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FooterLink(uiState.githubTitle, colors.accentPrimary) {
                uriHandler.openUri(uiState.githubUrl)
            }
            FooterLink(uiState.linkedinTitle, colors.accentPrimary) {
                uriHandler.openUri(uiState.linkedinUrl)
            }
        }
    }
}

@Composable
private fun FooterLink(
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier.clickable(onClick = onClick),
        style = MaterialTheme.typography.labelSmall.copy(
            color = color,
            fontWeight = FontWeight.Medium
        )
    )
}