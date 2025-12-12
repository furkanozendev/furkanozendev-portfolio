package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.profile_pic
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileWidget(
    modifier: Modifier = Modifier
) {
    // Theme Colors
    val keywordColor1 = Color(0xFFBD93F9)
    val keywordColor2 = Color(0xFF8BE9FD)
    val standardTextColor = Color(0xFFE0E0E0)

    // Pulse Animation for highlighted keywords
    val infiniteTransition = rememberInfiniteTransition()
    val colorAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    BentoCard(
        modifier = modifier,
        title = "User Profile",
        icon = Icons.Rounded.Person
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(24.dp)
        ) {
            val isVeryWide = maxWidth > 900.dp
            val isWide = maxWidth > 650.dp
            val isCompact = maxWidth < 480.dp

            // Typography scaling
            val titleSize = when {
                isVeryWide -> 56.sp
                isWide -> 40.sp
                else -> 28.sp
            }

            val subtitleSize = when {
                isVeryWide -> 24.sp
                isWide -> 20.sp
                else -> 16.sp
            }

            val descriptionSize = when {
                isVeryWide -> 18.sp
                isWide -> 16.sp
                else -> 14.sp
            }

            // Avatar size
            val avatarSize = when {
                isVeryWide -> 220.dp
                isWide -> 180.dp
                else -> 140.dp
            }

            val contentSpacing = if (isCompact) 16.dp else 24.dp

            val layoutModifier = Modifier.fillMaxWidth()

            val avatar: @Composable () -> Unit = {
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .aspectRatio(1f)
                        .border(
                            width = 3.dp,
                            brush = Brush.linearGradient(
                                listOf(keywordColor1, keywordColor2)
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(6.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFF15151A))
                ) {
                    Image(
                        painter = painterResource(Res.drawable.profile_pic),
                        contentDescription = "Furkan",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            if (isWide) {
                Row(
                    modifier = layoutModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(contentSpacing)
                ) {
                    avatar()

                    ProfileTextContent(
                        titleSize = titleSize,
                        subtitleSize = subtitleSize,
                        descriptionSize = descriptionSize,
                        keywordColor1 = keywordColor1,
                        keywordColor2 = keywordColor2,
                        standardTextColor = standardTextColor,
                        colorAlpha = colorAlpha
                    )
                }
            } else {
                Column(
                    modifier = layoutModifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(contentSpacing)
                ) {
                    avatar()

                    ProfileTextContent(
                        titleSize = titleSize,
                        subtitleSize = subtitleSize,
                        descriptionSize = descriptionSize,
                        keywordColor1 = keywordColor1,
                        keywordColor2 = keywordColor2,
                        standardTextColor = standardTextColor,
                        colorAlpha = colorAlpha,
                        center = true
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileTextContent(
    titleSize: TextUnit,
    subtitleSize: TextUnit,
    descriptionSize: TextUnit,
    keywordColor1: Color,
    keywordColor2: Color,
    standardTextColor: Color,
    colorAlpha: Float,
    center: Boolean = false
) {
    val alignment = if (center) Alignment.CenterHorizontally else Alignment.Start
    val textAlign = if (center) TextAlign.Center else TextAlign.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = alignment
    ) {
        // NAME (typewriter)
        TypewriterText(
            text = "Furkan Özen",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontSize = titleSize
            )
        )

        // TITLE
        Text(
            text = "Senior Android & Kotlin Engineer",
            style = MaterialTheme.typography.titleLarge.copy(
                color = keywordColor2,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = subtitleSize
            ),
            textAlign = textAlign
        )

        // SKILL CHIPS / TAGS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = if (center) Arrangement.Center else Arrangement.Start
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                SkillChip("Android · Compose")
                SkillChip("Kotlin Multiplatform")
                SkillChip("Compiler Plugins & KSP")
                SkillChip("Backend · Ktor")
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // DESCRIPTION (with pulsing keywords)
        val description = """
            I design scalable Kotlin architectures, from Android and KMP apps
            to AI-powered SDKs and compiler-level tooling. 
            Focused on clean, production-ready systems that companies can trust.
        """.trimIndent().replace("\n", " ")

        val keywords = listOf(
            "Android",
            "Kotlin Multiplatform",
            "AI-powered SDKs",
            "compiler-level tooling",
            "clean, production-ready systems"
        )

        val annotatedDescription = remember(description, keywords, colorAlpha) {
            buildAnnotatedString {
                var currentIndex = 0
                while (currentIndex < description.length) {
                    var matched = false
                    for (keyword in keywords) {
                        if (description.startsWith(keyword, startIndex = currentIndex)) {
                            withStyle(
                                style = SpanStyle(
                                    color = keywordColor1.copy(alpha = colorAlpha),
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(keyword)
                            }
                            currentIndex += keyword.length
                            matched = true
                            break
                        }
                    }
                    if (!matched) {
                        withStyle(style = SpanStyle(color = standardTextColor)) {
                            append(description[currentIndex])
                        }
                        currentIndex++
                    }
                }
            }
        }

        Text(
            text = annotatedDescription,
            fontSize = descriptionSize,
            textAlign = textAlign
        )
    }
}

@Composable
private fun SkillChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color(0x22FFFFFF))
            .border(
                width = 1.dp,
                color = Color(0x44FFFFFF),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFE0E0E0),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun TypewriterText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    typingSpeedMs: Long = 80
) {
    var displayedText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    val cursorHeight = with(density) { style.fontSize.toDp() }

    LaunchedEffect(text) {
        displayedText = ""
        showCursor = true
        text.forEachIndexed { index, _ ->
            delay(typingSpeedMs)
            displayedText = text.take(index + 1)
        }
        repeat(3) {
            delay(500)
            showCursor = !showCursor
        }
        showCursor = false
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = displayedText,
            style = style,
            modifier = modifier
        )

        if (showCursor) {
            Spacer(modifier = Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(cursorHeight)
                    .background(Color(0xFF00E676))
            )
        }
    }
}
