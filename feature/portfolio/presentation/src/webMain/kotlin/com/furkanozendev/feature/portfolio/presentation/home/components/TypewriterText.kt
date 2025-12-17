package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    text: String,
    style: TextStyle,
    backgroundColor: Color,
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
                    .background(backgroundColor)
            )
        }
    }
}
