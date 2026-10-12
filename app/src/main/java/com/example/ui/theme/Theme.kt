package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CineWorldColorScheme = darkColorScheme(
    primary = CinemaAmberGold,
    onPrimary = Color(0xFF140D00),
    primaryContainer = Color(0xFF332400),
    onPrimaryContainer = CinemaAmberGoldLight,
    secondary = CinemaRed,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF4A0508),
    onSecondaryContainer = CinemaRedLight,
    tertiary = CinemaNeonCyan,
    onTertiary = Color(0xFF00232A),
    background = CinemaBackground,
    onBackground = TextPrimary,
    surface = CinemaSurface,
    onSurface = TextPrimary,
    surfaceVariant = CinemaSurfaceElevated,
    onSurfaceVariant = TextSecondary,
    outline = CinemaSurfaceBorder
)

@Composable
fun CineWorldTheme(
    darkTheme: Boolean = true, // CineWorld defaults to cinematic dark aesthetic
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CineWorldColorScheme,
        typography = Typography,
        content = content
    )
}
