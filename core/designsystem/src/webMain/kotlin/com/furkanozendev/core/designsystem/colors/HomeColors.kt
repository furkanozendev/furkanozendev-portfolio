package com.furkanozendev.core.designsystem.colors

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HomeColors(
    /** Primary text - headings, titles, important content */
    val textPrimary: Color,

    /** Secondary text - body text, descriptions */
    val textSecondary: Color,

    /** Muted text - captions, labels, hints */
    val textMuted: Color,

    /** Tertiary text - less prominent than muted */
    val textTertiary: Color,

    /** On accent - text on accent-colored backgrounds */
    val textOnAccent: Color,

    /** Primary accent - links, buttons, highlights (cyan) */
    val accentPrimary: Color,

    /** Secondary accent - secondary highlights (purple) */
    val accentSecondary: Color,

    /** Tertiary accent - warm highlights (orange) */
    val accentTertiary: Color,

    /** Brand accent - brand color (blue) */
    val accentBrand: Color,

    /** Highlight text color for keywords */
    val textHighlight: Color,

    /** Base background - page/screen background */
    val surfaceBase: Color,

    /** Elevated surface - cards, modals */
    val surfaceElevated: Color,

    /** Glass surface - glass morphism backgrounds */
    val surfaceGlass: Color,

    /** Glass surface (muted) - less prominent glass */
    val surfaceGlassMuted: Color,

    /** Glass surface (subtle) - very subtle glass effect */
    val surfaceGlassSubtle: Color,

    /** Pill/chip background */
    val surfaceChip: Color,

    /** Focus pill background */
    val surfaceFocusPill: Color,

    /** Primary border - cards, containers */
    val borderPrimary: Color,

    /** Secondary border - subtle borders */
    val borderSecondary: Color,

    /** Accent border - highlighted borders */
    val borderAccent: Color,

    /** Glass border top - gradient border (brighter) */
    val borderGlassTop: Color,

    /** Glass border bottom - gradient border (dimmer) */
    val borderGlassBottom: Color,

    /** Status: Live - background */
    val statusLiveBg: Color,

    /** Status: Live - text */
    val statusLiveText: Color,

    /** Status: In Progress - background */
    val statusProgressBg: Color,

    /** Status: In Progress - text */
    val statusProgressText: Color,

    /** Status: Private - background */
    val statusPrivateBg: Color,

    /** Status: Private - text */
    val statusPrivateText: Color,

    /** Status: Neutral - background */
    val statusNeutralBg: Color,

    /** Status: Neutral - text */
    val statusNeutralText: Color,

    /** Timeline dot color */
    val timelineDot: Color,

    /** Timeline rail color */
    val timelineRail: Color,

    /** Active indicator dot */
    val indicatorActive: Color,

    /** System top bar background */
    val systemTopBarBg: Color,

    /** System bottom bar background */
    val systemBottomBarBg: Color,

    /** System bar indicator */
    val systemBarIndicator: Color,

    /** Profile image background */
    val profileImageBackground: Color,

    /** Blue glow effect */
    val glowBlue: Color,

    /** Orange glow effect */
    val glowOrange: Color,
)

/**
 * Dark theme colors (current production theme)
 */
val DarkHomeColors = HomeColors(
    // Text
    textPrimary = Color(0xFFEDEDED),
    textSecondary = Color(0xFFD8D8D8),
    textMuted = Color(0xFFB0B0B0),
    textTertiary = Color(0xFFAAAAAA),
    textOnAccent = Color(0xFF101015),
    
    // Accents
    accentPrimary = Color(0xFF8BE9FD),      // Cyan
    accentSecondary = Color(0xFFBD93F9),    // Purple
    accentTertiary = Color(0xFFFE7A36),     // Orange
    accentBrand = Color(0xFF3652AD),        // Blue
    textHighlight = Color(0xFFE0E0E0),
    
    // Surfaces
    surfaceBase = Color(0xFF0D0F14),
    surfaceElevated = Color(0xFF1E1E1E),
    surfaceGlass = Color.White.copy(alpha = 0.04f),
    surfaceGlassMuted = Color.White.copy(alpha = 0.05f),
    surfaceGlassSubtle = Color.White.copy(alpha = 0.06f),
    surfaceChip = Color.White.copy(alpha = 0.06f),
    surfaceFocusPill = Color(0xFF1E1E1E),
    
    // Borders
    borderPrimary = Color.White.copy(alpha = 0.08f),
    borderSecondary = Color.White.copy(alpha = 0.10f),
    borderAccent = Color(0xFF8BE9FD),
    borderGlassTop = Color.White.copy(alpha = 0.10f),
    borderGlassBottom = Color.White.copy(alpha = 0.02f),
    
    // Status
    statusLiveBg = Color(0x3327AE60),
    statusLiveText = Color(0xFF2ECC71),
    statusProgressBg = Color(0x33F1C40F),
    statusProgressText = Color(0xFFF1C40F),
    statusPrivateBg = Color(0x334E5DFF),
    statusPrivateText = Color(0xFF8BE9FD),
    statusNeutralBg = Color(0x33444444),
    statusNeutralText = Color(0xFFDDDDDD),
    
    // Special
    timelineDot = Color(0xFF6B6B6B),
    timelineRail = Color.White.copy(alpha = 0.14f),
    indicatorActive = Color(0xFF8BE9FD).copy(alpha = 0.8f),
    profileImageBackground = Color(0xFF0D0D12),
    
    // System Bars
    systemTopBarBg = Color(0xFF0D1117),
    systemBottomBarBg = Color(0xFF0D1117),
    systemBarIndicator = Color(0xFF797979),
    
    // Glows
    glowBlue = Color(0xFF3652AD).copy(alpha = 0.35f),
    glowOrange = Color(0xFFFE7A36).copy(alpha = 0.30f),
)

/**
 * Light theme colors
 */
val LightHomeColors = HomeColors(
    // Text
    textPrimary = Color(0xFF1A1A1A),
    textSecondary = Color(0xFF333333),
    textMuted = Color(0xFF666666),
    textTertiary = Color(0xFF888888),
    textOnAccent = Color(0xFFFFFFFF),
    
    // Accents
    accentPrimary = Color(0xFF0891B2),      // Darker Cyan
    accentSecondary = Color(0xFF7C3AED),    // Darker Purple
    accentTertiary = Color(0xFFEA580C),     // Darker Orange
    accentBrand = Color(0xFF2563EB),        // Blue
    textHighlight = Color(0xFF333333),
    
    // Surfaces
    surfaceBase = Color(0xFFF8FAFC),
    surfaceElevated = Color(0xFFFFFFFF),
    surfaceGlass = Color.Black.copy(alpha = 0.03f),
    surfaceGlassMuted = Color.Black.copy(alpha = 0.04f),
    surfaceGlassSubtle = Color.Black.copy(alpha = 0.05f),
    surfaceChip = Color.Black.copy(alpha = 0.05f),
    surfaceFocusPill = Color(0xFFF1F5F9),
    
    // Borders
    borderPrimary = Color.Black.copy(alpha = 0.08f),
    borderSecondary = Color.Black.copy(alpha = 0.12f),
    borderAccent = Color(0xFF0891B2),
    borderGlassTop = Color.Black.copy(alpha = 0.06f),
    borderGlassBottom = Color.Black.copy(alpha = 0.02f),
    
    // Status
    statusLiveBg = Color(0x2227AE60),
    statusLiveText = Color(0xFF16A34A),
    statusProgressBg = Color(0x22EAB308),
    statusProgressText = Color(0xFFCA8A04),
    statusPrivateBg = Color(0x224F46E5),
    statusPrivateText = Color(0xFF4F46E5),
    statusNeutralBg = Color(0x22666666),
    statusNeutralText = Color(0xFF525252),
    
    // Special
    timelineDot = Color(0xFFAAAAAA),
    timelineRail = Color.Black.copy(alpha = 0.12f),
    indicatorActive = Color(0xFF0891B2).copy(alpha = 0.9f),
    profileImageBackground = Color(0xFF0D0D12),
    
    // System Bars
    systemTopBarBg = Color(0xFFFFFFFF),
    systemBottomBarBg = Color(0xFFFFFFFF),
    systemBarIndicator = Color(0xFFBBBBBB),
    
    // Glows
    glowBlue = Color(0xFF2563EB).copy(alpha = 0.15f),
    glowOrange = Color(0xFFEA580C).copy(alpha = 0.12f),
)

val LocalHomeColors = staticCompositionLocalOf { DarkHomeColors }
