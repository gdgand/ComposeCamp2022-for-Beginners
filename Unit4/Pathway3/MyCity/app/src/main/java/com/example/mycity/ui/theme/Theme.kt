package com.example.mycity.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

// Material 3 color schemes
private val cityDarkColorScheme = darkColorScheme(
    primary = cityDarkPrimary,
    onPrimary = cityDarkOnPrimary,
    primaryContainer = cityDarkPrimaryContainer,
    onPrimaryContainer = cityDarkOnPrimaryContainer,
    inversePrimary = cityDarkPrimaryInverse,
    secondary = cityDarkSecondary,
    onSecondary = cityDarkOnSecondary,
    secondaryContainer = cityDarkSecondaryContainer,
    onSecondaryContainer = cityDarkOnSecondaryContainer,
    tertiary = cityDarkTertiary,
    onTertiary = cityDarkOnTertiary,
    tertiaryContainer = cityDarkTertiaryContainer,
    onTertiaryContainer = cityDarkOnTertiaryContainer,
    error = cityDarkError,
    onError = cityDarkOnError,
    errorContainer = cityDarkErrorContainer,
    onErrorContainer = cityDarkOnErrorContainer,
    background = cityDarkBackground,
    onBackground = cityDarkOnBackground,
    surface = cityDarkSurface,
    onSurface = cityDarkOnSurface,
    inverseSurface = cityDarkInverseSurface,
    inverseOnSurface = cityDarkInverseOnSurface,
    surfaceVariant = cityDarkSurfaceVariant,
    onSurfaceVariant = cityDarkOnSurfaceVariant,
    outline = cityDarkOutline
)

private val cityLightColorScheme = lightColorScheme(
    primary = cityLightPrimary,
    onPrimary = cityLightOnPrimary,
    primaryContainer = cityLightPrimaryContainer,
    onPrimaryContainer = cityLightOnPrimaryContainer,
    inversePrimary = cityLightPrimaryInverse,
    secondary = cityLightSecondary,
    onSecondary = cityLightOnSecondary,
    secondaryContainer = cityLightSecondaryContainer,
    onSecondaryContainer = cityLightOnSecondaryContainer,
    tertiary = cityLightTertiary,
    onTertiary = cityLightOnTertiary,
    tertiaryContainer = cityLightTertiaryContainer,
    onTertiaryContainer = cityLightOnTertiaryContainer,
    error = cityLightError,
    onError = cityLightOnError,
    errorContainer = cityLightErrorContainer,
    onErrorContainer = cityLightOnErrorContainer,
    background = cityLightBackground,
    onBackground = cityLightOnBackground,
    surface = cityLightSurface,
    onSurface = cityLightOnSurface,
    inverseSurface = cityLightInverseSurface,
    inverseOnSurface = cityLightInverseOnSurface,
    surfaceVariant = cityLightSurfaceVariant,
    onSurfaceVariant = cityLightOnSurfaceVariant,
    outline = cityLightOutline
)

@Composable
fun MyCityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val cityColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> cityDarkColorScheme
        else -> cityLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = cityColorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = cityColorScheme,
        typography = cityTypography,
        content = content
    )
}
