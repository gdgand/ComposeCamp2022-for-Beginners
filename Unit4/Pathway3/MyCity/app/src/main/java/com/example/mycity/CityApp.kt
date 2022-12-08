package com.example.mycity

import android.provider.ContactsContract
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.util.CityContentType
import com.example.mycity.util.CityNavigationType


@Composable
fun CityApp(windowSize: WindowWidthSizeClass
    , modifier: Modifier = Modifier
) {
    val viewModel: CityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val navigationType: CityNavigationType
    val contentType: CityContentType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = CityNavigationType.NAVIGATION_RAIL
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = CityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = CityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
    }
    CityHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        uiState = uiState,
        onTabPressed = { it: PlaceCategory ->
            viewModel.updateCurrentCategory(category = it)
            viewModel.resetHomeScreenState()
        },
        onPlaceCardPressed = { it: PlaceType->
            viewModel.updatePlace(place = it)
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenState()
        },
        modifier = modifier
    )

}


@Preview(showBackground = true)
@Composable
fun MyCityAppCompactPreview() {
    MyCityTheme() {
        CityApp(windowSize = WindowWidthSizeClass.Compact)
    }
}


@Preview(showBackground = true, widthDp = 700)
@Composable
fun MyCityAppMediumPreview() {
    MyCityTheme() {
        CityApp(windowSize = WindowWidthSizeClass.Medium)
    }
}



@Preview(showBackground = true, widthDp = 700)
@Composable
fun MyCityAppExpandedPreview() {
    MyCityTheme() {
        CityApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}
