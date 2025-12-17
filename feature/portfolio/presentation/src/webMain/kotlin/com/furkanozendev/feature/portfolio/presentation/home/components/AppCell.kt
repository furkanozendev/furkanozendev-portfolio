package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.cv_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.github_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.gmail_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.linkedin_ic
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private data class SocialAppItem(
    val title: String,
    val icon: DrawableResource,
    val url: String
)

@Composable
fun AppCell(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val res = LocalStringResources.current

    val apps = remember {
        listOf(
            SocialAppItem(res["linkedin_title"], Res.drawable.linkedin_ic, res["linkedin_url"]),
            SocialAppItem(res["github_title"], Res.drawable.github_ic, res["github_url"]),
            SocialAppItem(res["email_title"], Res.drawable.gmail_ic, res["email_url"]),
            SocialAppItem(res["download_cv_title"], Res.drawable.cv_ic, res["download_cv_url"])
        )
    }

    BoxWithConstraints(modifier = modifier) {
        val compact = maxWidth < 520.dp
        val columns = if (compact) 2 else 4
        val gap = if (compact) 10.dp else 12.dp

        val rows = remember(apps, columns) { apps.chunked(columns) }

        Column(verticalArrangement = Arrangement.spacedBy(gap)) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(gap)
                ) {
                    row.forEach { item ->
                        SocialTile(
                            item = item,
                            onClick = { uriHandler.openUri(item.url) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
private fun SocialTile(
    item: SocialAppItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalHomeColors.current

    val bg = Color.White.copy(alpha = 0.04f)
    val border = Color.White.copy(alpha = 0.08f)
    val iconBg = Color.White.copy(alpha = 0.06f)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .background(iconBg, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(26.dp),
                tint = colors.textPrimary
            )
        }

        Text(
            text = item.title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.textPrimary
        )
    }
}
