package com.example.mycity

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.ui.theme.MyCityTheme


@Composable
fun CityApp(windowSize: WindowWidthSizeClass
    , modifier: Modifier = Modifier
) {
    val viewModel: CityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value



}


@Preview(showBackground = true, widthDp = 700)
@Composable
fun CityAppPreview() {
    MyCityTheme() {
        CityApp(windowSize = WindowWidthSizeClass.Medium)
    }
}