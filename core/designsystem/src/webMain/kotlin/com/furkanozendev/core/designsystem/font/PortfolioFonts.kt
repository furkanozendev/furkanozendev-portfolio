package com.furkanozendev.core.designsystem.font

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import furkanozendev.core.designsystem.generated.resources.Res
import furkanozendev.core.designsystem.generated.resources.inter_medium
import furkanozendev.core.designsystem.generated.resources.inter_regular
import furkanozendev.core.designsystem.generated.resources.inter_semibold
import furkanozendev.core.designsystem.generated.resources.jetbrains_mono_medium
import furkanozendev.core.designsystem.generated.resources.jetbrains_mono_regular
import furkanozendev.core.designsystem.generated.resources.jetbrains_mono_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun InterFamily(): FontFamily = FontFamily(
    Font(Res.font.inter_regular, FontWeight.Normal),
    Font(Res.font.inter_medium, FontWeight.Medium),
    Font(Res.font.inter_semibold, FontWeight.SemiBold),
)

@Composable
fun JetBrainsMonoFamily(): FontFamily = FontFamily(
    Font(Res.font.jetbrains_mono_regular, weight = FontWeight.Normal),
    Font(Res.font.jetbrains_mono_medium, weight = FontWeight.Medium),
    Font(Res.font.jetbrains_mono_semibold, weight = FontWeight.SemiBold),
)

@Composable
fun AppTypography(): Typography {
    val inter = InterFamily()
    val jetbrainsMono = JetBrainsMonoFamily()

    return remember(inter, jetbrainsMono) {
        Typography(
            displayLarge = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.SemiBold,
                fontSize = 56.sp,
                lineHeight = 64.sp
            ),
            displayMedium = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.SemiBold,
                fontSize = 44.sp,
                lineHeight = 52.sp
            ),
            displaySmall = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp,
                lineHeight = 44.sp
            ),

            headlineLarge = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                lineHeight = 40.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 36.sp
            ),
            headlineSmall = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                lineHeight = 32.sp
            ),
            titleLarge = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 28.sp
            ),
            titleMedium = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 22.sp
            ),
            titleSmall = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            bodyMedium = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            bodySmall = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 18.sp
            ),
            labelLarge = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                lineHeight = 18.sp
            ),
            labelMedium = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 16.sp
            ),
            labelSmall = TextStyle(
                fontFamily = jetbrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}
