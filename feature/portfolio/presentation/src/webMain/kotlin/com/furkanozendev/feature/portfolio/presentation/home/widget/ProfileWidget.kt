package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.profile_pic
import kotlinx.coroutines.delay
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
    // Breakpoints (keep minimal, stable)
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
    modifier: Modifier = Modifier,
    name: String = "Furkan Özen",
    title: String = "Senior Android & Kotlin Engineer",
    tagline: String = "Building fast, scalable Android experiences",
) {
    val keywordColor1 = Color(0xFF8BE9FD)
    val keywordColor2 = Color(0xFFFE7A36)

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
                    contentDescription = name
                )
            },
            text = {
                ProfileTextContent(
                    name = name,
                    title = title,
                    tagline = tagline,
                    titleSize = spec.titleSize,
                    subtitleSize = spec.subtitleSize,
                    taglineSize = spec.taglineSize,
                    keywordColor1 = keywordColor1,
                    keywordColor2 = keywordColor2,
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
                color = Color(0xFFEDEDED),
                fontFamily = FontFamily.Monospace,
                fontSize = titleSize,
                lineHeight = (titleSize.value + 2).sp
            ),
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

@Composable
fun TypewriterText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    enabled: Boolean = true,
    typingSpeedMs: Long = 55L,
    cursorBlinkMs: Long = 450L,
) {
    var displayed by remember(text, enabled) { mutableStateOf(if (enabled) "" else text) }
    var showCursor by remember(text, enabled) { mutableStateOf(enabled) }

    LaunchedEffect(text, enabled) {
        if (!enabled) return@LaunchedEffect

        displayed = ""
        showCursor = true

        for (i in text.indices) {
            delay(typingSpeedMs)
            displayed = text.take(i + 1)
        }

        repeat(3) {
            delay(cursorBlinkMs)
            showCursor = !showCursor
        }
        showCursor = false
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = displayed,
            style = style,
            textAlign = textAlign
        )

        if (enabled && showCursor) {
            Spacer(Modifier.width(6.dp))
            Box(
                Modifier
                    .width(8.dp)
                    .height(
                        with(LocalDensity.current) {
                            (style.lineHeight.takeIf { it != TextUnit.Unspecified } ?: style.fontSize).toDp()
                        }
                    )
                    .background(Color(0xFF3652AD))
            )
        }
    }
}

@Composable
fun GradientBorderAvatar(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = 84.dp,
    borderWidth: Dp = 3.dp,
    outerRadius: Dp = 22.dp,
    innerRadius: Dp = 18.dp,
    gradient: Brush = Brush.linearGradient(
        colors = listOf(Color(0xFF3652AD), Color(0xFFFE7A36))
    ),
    background: Color = Color(0xFF0D0D12),
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(outerRadius))
            .background(gradient)
            .padding(borderWidth)
            .clip(RoundedCornerShape(innerRadius))
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
