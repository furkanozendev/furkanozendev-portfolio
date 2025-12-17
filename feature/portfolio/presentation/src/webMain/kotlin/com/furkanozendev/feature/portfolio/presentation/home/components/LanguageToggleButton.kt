package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.furkanozendev.feature.portfolio.domain.model.AppLanguage
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources

@Composable
fun LanguageToggleButton(
    modifier: Modifier = Modifier,
    initialLanguage: AppLanguage,
    onLanguageChanged: (AppLanguage) -> Unit = {}
) {
    var currentLanguage by remember { mutableStateOf(initialLanguage) }

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor = if (isHovered) {
        Color.White.copy(alpha = 0.15f)
    } else {
        Color.Black.copy(alpha = 0.2f)
    }

    val borderColor = if (isHovered) {
        Color.White.copy(alpha = 0.3f)
    } else {
        Color.White.copy(alpha = 0.1f)
    }

    val contentColor = Color.White.copy(alpha = 0.9f)
    val buttonShape = RoundedCornerShape(percent = 50)

    Box(
        modifier = modifier
            .clip(buttonShape)
            .background(backgroundColor)
            .border(width = 1.dp, color = borderColor, shape = buttonShape)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                val newLang = if (currentLanguage == AppLanguage.EN) AppLanguage.TR else AppLanguage.EN
                currentLanguage = newLang
                onLanguageChanged(newLang)
            }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Language,
                contentDescription = LocalStringResources.current["change_language"],
                tint = contentColor,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = currentLanguage.name,
                color = contentColor,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}