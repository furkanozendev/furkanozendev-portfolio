package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.papara_logo
import org.jetbrains.compose.resources.painterResource


val androidDevDescription = listOf(
    "Owned and improved multiple card-domain features including card transactions, card details, and card apply flows.",
    "Developed VoiceCard, an accessibility-focused feature using BLE and text-to-speech for visually impaired users.",
    "Built SketchMyCard, enabling users to design and customize their own cards through a canvas-based UI.",
    "Led refactoring and Compose migration across the Cashback domain and contributed reusable UI components.",
    "Helped modularize legacy modules into Clean Architecture and improved navigation/state structures.",
    "Added unit tests, introduced Maestro flow tests, and contributed to Papara’s Compose testing standards.",
    "Worked cross-domain on new Investment features to improve user UX for US market investments."
)

val internDescription = listOf(
    "Delivered bug fixes and improvements across onboarding flows including login and registration.",
    "Contributed to KYC screens with functional and UX updates.",
    "Migrated legacy screens from Data Binding to View Binding and assisted MVVM transitions.",
    "Refactored small features and stabilized frequently-used flows under senior guidance."
)


@Composable
fun ExperienceWidget(
    modifier: Modifier = Modifier
) {
    BentoCard(
        modifier = modifier,
        title = "Experience",
        icon = Icons.Rounded.Work
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.papara_logo),
                    contentDescription = "Papara",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        "Papara",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        "Feb 2022 — Dec 2025 · 3 yrs 10 mos",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFCCCCCC)
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                ExperienceTreeNode(
                    title = "Android Developer",
                    date = "Apr 2022 — Dec 2025",
                    description = androidDevDescription
                )

                ExperienceTreeNode(
                    title = "Android Developer Intern",
                    date = "Feb 2022 — Apr 2022",
                    description = internDescription,
                    isLast = true
                )
            }
        }
    }
}

@Composable
fun ExperienceTreeNode(
    title: String,
    date: String,
    description: List<String>,
    isLast: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .padding(top = 20.dp)
                        .fillMaxHeight()
                        .background(Color.DarkGray)
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(12.dp)
                    .background(Color.Gray, CircleShape)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
            Text(
                date,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFBBBBBB)
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                description.forEach { line ->
                    Text(
                        "• $line",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFFDDDDDD),
                            lineHeight = 18.sp
                        )
                    )
                }
            }

            if (!isLast) {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
