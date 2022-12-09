package com.example.myjeju.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = LightGreen900,
    surface = LightGreen700,
    onSurface = White,
    primary = Grey900,
    onPrimary = White,
    secondary = Grey100
)

private val LightColorPalette = lightColors(
    background = OwlBlue700,
    surface = White,
//    onSurface = Grey900,
    primary = OwlBlue700,
//    onPrimary = Grey900,
//    secondary = Grey700

)

//private val DarkColorPalette = darkColors(
//    background = OwlBlue700,
//    surface = OwlBlue700,
////    onSurface = OwlBlue700,
//    onPrimary = OwlBlue700,
////    primaryVariant = Purple700,
//    secondary = OwlBlue700
//
////    background = LightGreen900,
////    surface = LightGreen700,
////    onSurface = White,
////    primary = Grey900,
////    onPrimary = White,
////    secondary = Grey100
//)
//
//private val LightColorPalette = lightColors(
//    background = OwlBlue700,
//    surface = OwlBlue700,
////    onSurface = OwlBlue700,
//    onPrimary = OwlBlue700,
////    primaryVariant = Purple700,
//    secondary = OwlBlue700
//
////    background = OwlYellow700,
////    onSurface = Black,
////    primary = OwlYellow700,
////    secondary = Teal200
//
////    primary = Purple500,
////    primaryVariant = Purple700,
////    secondary = Teal200
//
//    /* Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
//    */
//)

@Composable
fun MyJejuTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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