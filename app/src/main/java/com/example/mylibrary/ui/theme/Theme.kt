package com.example.mylibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrandGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD5E8D2),
    onPrimaryContainer = Color(0xFF0F2712),
    secondary = Gold,
    onSecondary = Color(0xFF2A2000),
    secondaryContainer = Color(0xFFF3E5AE),
    onSecondaryContainer = Color(0xFF3A2D00),
    tertiary = Terracotta,
    onTertiary = Color.White,
    background = Cream,
    onBackground = Color(0xFF1C1B16),
    surface = Cream,
    onSurface = Color(0xFF1C1B16),
    surfaceVariant = Color(0xFFE9E4D2),
    onSurfaceVariant = Color(0xFF5A5848),
    outline = Color(0xFF8C8A78),
    error = Terracotta,
    onError = Color.White,
    errorContainer = Color(0xFFF8DAD3),
    onErrorContainer = Color(0xFF410E05)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF9CCB98),
    onPrimary = Color(0xFF0E2A12),
    primaryContainer = BrandGreen,
    onPrimaryContainer = Color(0xFFD5E8D2),
    secondary = Color(0xFFE6C75A),
    onSecondary = Color(0xFF3A2D00),
    secondaryContainer = Color(0xFF574500),
    onSecondaryContainer = Color(0xFFF3E5AE),
    tertiary = Color(0xFFFFB4A2),
    background = Color(0xFF12160F),
    onBackground = Color(0xFFE5E3D6),
    surface = Color(0xFF12160F),
    onSurface = Color(0xFFE5E3D6),
    surfaceVariant = Color(0xFF3A3E33),
    onSurfaceVariant = Color(0xFFC6C8B8),
    outline = Color(0xFF90927F),
    error = Color(0xFFFFB4A2),
    onError = Color(0xFF410E05),
    errorContainer = Color(0xFF5C1A0C),
    onErrorContainer = Color(0xFFF8DAD3)
)

@Composable
fun MyLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}