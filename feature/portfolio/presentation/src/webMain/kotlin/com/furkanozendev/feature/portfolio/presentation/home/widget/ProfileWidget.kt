package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.feature.portfolio.presentation.home.components.GradientBorderAvatar
import com.furkanozendev.feature.portfolio.presentation.home.components.TypewriterText
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.profile_pic
import org.jetbrains.compose.resources.painterResource

@Immutable
private data class ProfileWidgetSpec(
    val isHorizontal: Boolean,
    val avatarSize: Dp,
    val gap: Dp,
    val contentPadding: PaddingValues,
    val textAlign: TextAlign,
    val horizontalAlignment: Alignment.Horizontal,
    val titleSize: TextUnit,
    val subtitleSize: TextUnit,
    val taglineSize: TextUnit,
    val maxTextWidth: Dp,
)

@Composable
private fun rememberProfileWidgetSpec(maxWidth: Dp): ProfileWidgetSpec {
    val isCompact = maxWidth < 520.dp
    val isWide = maxWidth >= 720.dp
    val isVeryWide = maxWidth >= 1000.dp

    val contentPadding = when {
        isVeryWide -> PaddingValues(horizontal = 52.dp, vertical = 52.dp)
        isWide -> PaddingValues(horizontal = 40.dp, vertical = 44.dp)
        isCompact -> PaddingValues(horizontal = 20.dp, vertical = 28.dp)
        else -> PaddingValues(horizontal = 28.dp, vertical = 36.dp)
    }

    val title = when {
        isVeryWide -> 62.sp
        isWide -> 46.sp
        else -> 34.sp
    }

    val subtitle = when {
        isVeryWide -> 24.sp
        isWide -> 20.sp
        else -> 16.sp
    }

    val tagline = when {
        isVeryWide -> 20.sp
        isWide -> 18.sp
        else -> 16.sp
    }

    val avatar = when {
        isVeryWide -> 220.dp
        isWide -> 180.dp
        else -> 140.dp
    }

    return remember(maxWidth) {
        ProfileWidgetSpec(
            isHorizontal = isWide,
            avatarSize = avatar,
            gap = if (isCompact) 16.dp else 24.dp,
            contentPadding = contentPadding,
            textAlign = if (isWide) TextAlign.Start else TextAlign.Center,
            horizontalAlignment = if (isWide) Alignment.Start else Alignment.CenterHorizontally,
            titleSize = title,
            subtitleSize = subtitle,
            taglineSize = tagline,
            maxTextWidth = if (isVeryWide) 720.dp else 640.dp
        )
    }
}

@Composable
fun ProfileWidget(
    modifier: Modifier = Modifier
) {
    val res = LocalStringResources.current
    val colors = LocalHomeColors.current

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val spec = rememberProfileWidgetSpec(maxWidth)

        ProfileHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spec.contentPadding),
            spec = spec,
            avatar = {
                GradientBorderAvatar(
                    painter = painterResource(Res.drawable.profile_pic),
                    size = spec.avatarSize,
                    contentDescription = res["name"],
                    gradient = Brush.linearGradient(
                        colors = listOf(colors.glowBlue, colors.glowOrange)
                    ),
                    background = colors.profileImageBackground
                )
            },
            text = {
                ProfileTextContent(
                    name = res["name"],
                    title = res["title"],
                    tagline = res["tagline"],
                    titleSize = spec.titleSize,
                    subtitleSize = spec.subtitleSize,
                    taglineSize = spec.taglineSize,
                    keywordColor1 = colors.accentPrimary,
                    keywordColor2 = colors.accentTertiary,
                    textPrimary = colors.textPrimary,
                    backgroundColor = colors.glowBlue,
                    textAlign = spec.textAlign,
                    horizontalAlignment = spec.horizontalAlignment,
                    maxWidth = spec.maxTextWidth
                )
            }
        )
    }
}

@Composable
private fun ProfileHeader(
    modifier: Modifier,
    spec: ProfileWidgetSpec,
    avatar: @Composable () -> Unit,
    text: @Composable () -> Unit,
) {
    if (spec.isHorizontal) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spec.gap)
        ) {
            avatar()
            Box(modifier = Modifier.weight(1f)) { text() }
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spec.gap)
        ) {
            avatar()
            text()
        }
    }
}

@Composable
private fun ProfileTextContent(
    name: String,
    title: String,
    tagline: String,
    titleSize: TextUnit,
    subtitleSize: TextUnit,
    taglineSize: TextUnit,
    keywordColor1: Color,
    keywordColor2: Color,
    textPrimary: Color,
    backgroundColor: Color,
    textAlign: TextAlign,
    horizontalAlignment: Alignment.Horizontal,
    maxWidth: Dp,
) {
    Column(
        modifier = Modifier
            .widthIn(max = maxWidth)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        TypewriterText(
            text = name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = textPrimary,
                fontFamily = FontFamily.Monospace,
                fontSize = titleSize,
                lineHeight = (titleSize.value + 2).sp
            ),
            backgroundColor = backgroundColor,
            textAlign = textAlign
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = keywordColor1,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = subtitleSize
            ),
            textAlign = textAlign
        )

        Text(
            text = tagline,
            style = MaterialTheme.typography.titleMedium.copy(
                color = keywordColor2,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = taglineSize
            ),
            textAlign = textAlign
        )
    }
}
