package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.project_duelist_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_github_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_portfolio_banner
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

enum class ProjectStatusStyle { Live, InProgress, Neutral }

data class ProjectUiModel(
    val bannerPainter: Painter,
    val title: String,
    val status: String,
    val statusStyle: ProjectStatusStyle,
    val description: String,
    val techStack: List<String>,
    val primaryActionLabel: String? = null,
    val onPrimaryClick: (() -> Unit)? = null,
    val secondaryActionLabel: String? = null,
    val onSecondaryClick: (() -> Unit)? = null
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProjectsWidget(
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    val portfolioLiveUrl = "https://your-portfolio-url.com"
    val portfolioRepoUrl = "https://github.com/furkanozendev/your-portfolio-repo"
    val githubProfileUrl = "https://github.com/furkanozendev"

    val projects = listOf(
        ProjectUiModel(
            bannerPainter = painterResource(Res.drawable.project_portfolio_banner),
            title = "Portfolio · Compose Multiplatform Web",
            status = "Live · Personal site",
            statusStyle = ProjectStatusStyle.Live,
            description = "Personal portfolio built with Kotlin + Compose Multiplatform for the web, with system-inspired UI, Bento widgets, and responsive layouts.",
            techStack = listOf(
                "Kotlin",
                "Compose Multiplatform",
                "Compose for Web",
                "Gradle",
                "Kotlin Multiplatform"
            ),
            primaryActionLabel = "View live",
            onPrimaryClick = { uriHandler.openUri(portfolioLiveUrl) },
            secondaryActionLabel = "View code",
            onSecondaryClick = { uriHandler.openUri(portfolioRepoUrl) }
        ),
        ProjectUiModel(
            bannerPainter = painterResource(Res.drawable.project_duelist_banner),
            title = "DuelistAI · Card Battler",
            status = "In development · Private project",
            statusStyle = ProjectStatusStyle.InProgress,
            description = "A Kotlin Multiplatform card battler game exploring AI-generated cards, a Ktor/Supabase backend, and Compose-based UI. Currently focused on prototyping game systems and infrastructure — source code is private and not released to stores yet.",
            techStack = listOf(
                "Kotlin Multiplatform",
                "Compose Multiplatform",
                "Ktor · Supabase",
                "PostgreSQL",
                "Clean Architecture",
                "Pixel-art UI"
            )
        ),
        ProjectUiModel(
            bannerPainter = painterResource(Res.drawable.project_github_banner),
            title = "More Projects on GitHub",
            status = "Public repositories",
            statusStyle = ProjectStatusStyle.Neutral,
            description = "Includes experiments with Kotlin compiler plugins, Gradle convention plugins, analytics SDKs, Compose samples, and backend services.",
            techStack = listOf(
                "Kotlin",
                "Compiler Plugins / KSP",
                "Gradle Plugins",
                "Android · Compose",
                "Backend APIs"
            ),
            primaryActionLabel = "Open GitHub profile",
            onPrimaryClick = { uriHandler.openUri(githubProfileUrl) }
        )
    )

    var currentIndex by remember { mutableStateOf(0) }
    val lastIndex = projects.lastIndex

    // Auto-slide, but restart timer when user changes item
    LaunchedEffect(currentIndex, projects.size) {
        delay(8000)
        currentIndex = (currentIndex + 1) % projects.size
    }

    BentoCard(
        modifier = modifier,
        title = "Projects",
        icon = Icons.Rounded.Apps
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Slider row: [ ← ] [ content ] [ → ]
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                val isNarrow = maxWidth < 520.dp

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconButton(
                        onClick = {
                            currentIndex =
                                if (currentIndex == 0) lastIndex else currentIndex - 1
                        },
                        modifier = if (isNarrow) {
                            Modifier.size(32.dp)
                        } else {
                            Modifier.size(40.dp)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "Previous project",
                            tint = Color(0xFFB0B0B0)
                        )
                    }

                    // Animated slide area
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        AnimatedContent(
                            targetState = currentIndex,
                            transitionSpec = {
                                val direction = if (targetState > initialState) 1 else -1
                                (slideInHorizontally(
                                    animationSpec = tween(durationMillis = 400)
                                ) { fullWidth -> direction * fullWidth } +
                                        fadeIn(animationSpec = tween(400))).togetherWith(
                                    slideOutHorizontally(
                                        animationSpec = tween(durationMillis = 400)
                                    ) { fullWidth -> -direction * fullWidth } +
                                            fadeOut(animationSpec = tween(400))
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) { index ->
                            val project = projects[index]
                            ProjectCard(
                                modifier = Modifier.fillMaxWidth(),
                                bannerPainter = project.bannerPainter,
                                title = project.title,
                                status = project.status,
                                statusStyle = project.statusStyle,
                                description = project.description,
                                techStack = project.techStack,
                                primaryActionLabel = project.primaryActionLabel,
                                onPrimaryClick = project.onPrimaryClick,
                                secondaryActionLabel = project.secondaryActionLabel,
                                onSecondaryClick = project.onSecondaryClick
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            currentIndex =
                                if (currentIndex == lastIndex) 0 else currentIndex + 1
                        },
                        modifier = if (isNarrow) {
                            Modifier.size(32.dp)
                        } else {
                            Modifier.size(40.dp)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronRight,
                            contentDescription = "Next project",
                            tint = Color(0xFFB0B0B0)
                        )
                    }
                }
            }

            // Dots indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                projects.forEachIndexed { index, _ ->
                    val selected = index == currentIndex
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (selected) 10.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (selected) Color(0xFF8BE9FD) else Color(0xFF3A3A45)
                            )
                            .clickable { currentIndex = index }
                    )
                }
            }
        }
    }
}


@Composable
private fun ProjectCard(
    modifier: Modifier = Modifier,
    bannerPainter: Painter,
    title: String,
    status: String,
    statusStyle: ProjectStatusStyle,
    description: String,
    techStack: List<String>,
    primaryActionLabel: String?,
    onPrimaryClick: (() -> Unit)?,
    secondaryActionLabel: String?,
    onSecondaryClick: (() -> Unit)?
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val isWideLayout = maxWidth > 800.dp

        // Compact banner heights so content always has room
        val bannerHeightWide = 120.dp     // desktop
        val bannerHeightNarrow = 160.dp   // mobile / tablet

        if (isWideLayout) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProjectBanner(
                    bannerPainter = bannerPainter,
                    modifier = Modifier
                        .weight(0.4f)
                        .height(bannerHeightWide)
                )

                ProjectContent(
                    title = title,
                    status = status,
                    statusStyle = statusStyle,
                    description = description,
                    techStack = techStack,
                    primaryActionLabel = primaryActionLabel,
                    onPrimaryClick = onPrimaryClick,
                    secondaryActionLabel = secondaryActionLabel,
                    onSecondaryClick = onSecondaryClick,
                    modifier = Modifier.weight(0.6f)
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProjectBanner(
                    bannerPainter = bannerPainter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(bannerHeightNarrow)
                )

                ProjectContent(
                    title = title,
                    status = status,
                    statusStyle = statusStyle,
                    description = description,
                    techStack = techStack,
                    primaryActionLabel = primaryActionLabel,
                    onPrimaryClick = onPrimaryClick,
                    secondaryActionLabel = secondaryActionLabel,
                    onSecondaryClick = onSecondaryClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ProjectBanner(
    bannerPainter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = bannerPainter,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxSize(),           // width + height come from caller
        contentScale = ContentScale.Crop
    )
}


@Composable
private fun ProjectContent(
    title: String,
    status: String,
    statusStyle: ProjectStatusStyle,
    description: String,
    techStack: List<String>,
    primaryActionLabel: String?,
    onPrimaryClick: (() -> Unit)?,
    secondaryActionLabel: String?,
    onSecondaryClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        ProjectStatusPill(
            text = status,
            style = statusStyle
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFFDDDDDD),
                lineHeight = 18.sp
            )
        )

        FlowRow(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            techStack.forEach { tech ->
                ProjectTechChip(text = tech)
            }
        }

        if (primaryActionLabel != null && onPrimaryClick != null) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProjectButton(
                    label = primaryActionLabel,
                    filled = true,
                    onClick = onPrimaryClick
                )

                if (secondaryActionLabel != null && onSecondaryClick != null) {
                    ProjectButton(
                        label = secondaryActionLabel,
                        filled = false,
                        onClick = onSecondaryClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectStatusPill(
    text: String,
    style: ProjectStatusStyle
) {
    val (bg, fg) = when (style) {
        ProjectStatusStyle.Live -> Color(0x3327AE60) to Color(0xFF2ECC71)
        ProjectStatusStyle.InProgress -> Color(0x33F1C40F) to Color(0xFFF1C40F)
        ProjectStatusStyle.Neutral -> Color(0x33444444) to Color(0xFFDDDDDD)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = fg,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun ProjectTechChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color(0x22FFFFFF))
            .border(
                width = 1.dp,
                color = Color(0x33FFFFFF),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFE0E0E0),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun ProjectButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit
) {
    val bg = if (filled) Color(0xFF8BE9FD) else Color.Transparent
    val fg = if (filled) Color(0xFF101015) else Color(0xFF8BE9FD)
    val border = if (filled) Color.Transparent else Color(0xFF8BE9FD)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .border(
                width = 1.dp,
                color = border,
                shape = RoundedCornerShape(999.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = fg,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}