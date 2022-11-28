
package com.example.android.unscramble.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Indigo200,
    primaryVariant = Indigo800,
    secondary = Light_blue200,
    onSecondary = Black,
    onPrimary = Black,
    secondaryVariant = Light_blue200,
    onBackground = White,
    onSurface = White,
    background = Black,
    surface = Black,
    error = Red400
)

private val LightColorPalette = lightColors(
    primary = Indigo500,
    primaryVariant = Indigo800,
    secondary = Light_blue200,
    onSecondary = Black,
    onPrimary = White,
    secondaryVariant = Light_blue700,
    onBackground = Black,
    onSurface = Black,
    background = White,
    surface = White,
    error = Red700
)

@Composable
fun UnscrambleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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