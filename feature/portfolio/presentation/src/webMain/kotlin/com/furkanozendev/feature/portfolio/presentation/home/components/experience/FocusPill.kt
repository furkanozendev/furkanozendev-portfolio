package com.furkanozendev.feature.portfolio.presentation.home.components.experience

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun FocusPill(
    heading: String,
    highlight: String,
    colors: HomeColors,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surfaceFocusPill)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.borderGlassTop,
                        colors.borderGlassBottom
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = heading.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                color = colors.accentPrimary.copy(alpha = 0.9f),
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )

        Text(
            text = highlightKeywords(highlight, highlightColor = colors.textHighlight),
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textTertiary,
                lineHeight = 16.sp
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun highlightKeywords(
    text: String,
    highlightColor: Color
): AnnotatedString {
    return buildAnnotatedString {
        val parts = text.split("*")
        parts.forEachIndexed { index, part ->
            if (index % 2 == 1) {
                withStyle(
                    style = SpanStyle(
                        color = highlightColor,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(part)
                }
            } else {
                append(part)
            }
        }
    }
}
