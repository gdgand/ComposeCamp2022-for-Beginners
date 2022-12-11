package com.example.superhero.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    onPrimary = md_theme_dark_onSurface,
    onSecondary = md_theme_dark_primary,
    onBackground = md_theme_dark_onPrimary,
    onSurface = md_theme_dark_secondary
)

private val LightColorPalette = lightColors(
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    onPrimary = md_theme_light_onSurface,
    onSecondary = md_theme_light_primary,
    onBackground = md_theme_light_onPrimary,
    onSurface = md_theme_light_secondary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SuperHeroTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}