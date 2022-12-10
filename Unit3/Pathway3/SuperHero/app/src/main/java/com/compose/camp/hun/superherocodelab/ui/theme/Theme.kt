package com.compose.camp.hun.superherocodelab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/**
 * Theme.kt 파일
 * Theme파일은 색상, 서체, 도형으로 정의되는 앱 테마에 관한 모든 정보를 담고있는 파일.
 *
 * 해당 파일에는 SuperheroesTheme가 정의되어 있다. (Woof 파일에는 WoofTheme 함수가 정의되어 있음)
 * SuperheroesTheme파일에는 앱의 색상, 서체, 도형을 설정한다.
 *
 * 이를 MainActivity에서 사용할 땐
 * ```kotlin
 * setContent {
 *     SuperheroesTheme {
 *         SuperheroesApp()
 *     }
 * }
 * ```
 * 형태로 사용하고, SuperheroesTheme 내부에서 MaterialTheme의 content로 SuperheroesApp 내용을 전달한다.
 */

/**
 * darkTheme 컬러 설정
 * 각 slot(background, surface ....)에 색상 값 설정
 * 각각 역할을 하는 slot에 값을 설정하면 일관된 디자인 시스템을 제공한다. (Surface 함수 배경색은 surface색상으로 설정된다.)
 *
 * 이렇게 설정된 색상은 MainActivity에서.... MaterialTheme.colors.onSurface 등으로 접근할...수.. 있나..?
 */
private val DarkColorPalette = darkColors(
    background = md_theme_dark_background,
    surface = md_theme_dark_surface, // 카드, 시트, 메뉴와 같은 구성요소의 표면에 적용되는 색상.
    onSurface = md_theme_dark_onSurface, // surface구성요소 위에 적용되는 색상.
    primary = md_theme_dark_primary, // 앱 화면과 구성요소에 가장 자주 표시되는 색상.
    onPrimary = md_theme_dark_onPrimary, // primary 위에 나타나는 색상.
    secondary = md_theme_dark_secondary, // 앱을 강조하고 구분하는데 사용.
)

private val LightColorPalette = lightColors(
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary
)

@Composable
fun SuperheroesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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