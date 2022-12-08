package com.example.mycity

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import androidx.compose.material.icons.outlined.*
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.NavigationItemContent
import com.example.mycity.data.NavigationItemContentList
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.util.CityContentType
import com.example.mycity.util.CityNavigationType


@Composable
fun CityHomeScreen(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    uiState: CityUiState,
    onTabPressed: (PlaceCategory) -> Unit,
    onPlaceCardPressed: (PlaceType) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (navigationType == CityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
//        val navigationDrawerContentDescription = stringResource(R.string.navigation_drawer)
//        PermanentNavigationDrawer(
//            drawerContent = {
//                PermanentDrawerSheet(Modifier.width(240.dp)) {
//                    NavigationDrawerContent(
//                        selectedDestination = replyUiState.currentMailbox,
//                        onTabPressed = onTabPressed,
//                        navigationItemContentList = navigationItemContentList
//                    )
//                }
//            },
//            modifier = Modifier.testTag(navigationDrawerContentDescription)
//        ) {
//            ReplyAppContent(
//                navigationType = navigationType,
//                contentType = contentType,
//                replyUiState = replyUiState,
//                onTabPressed = onTabPressed,
//                onEmailCardPressed = onEmailCardPressed,
//                navigationItemContentList = navigationItemContentList,
//                modifier = modifier
//            )
//        }
    } else {
        if (uiState.isShowingHome) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                onTabPressed = onTabPressed,
                onPlaceCardPressed = onPlaceCardPressed,
                modifier = modifier
            )
        } else {
            CityDetailsScreen(
                uiState = uiState,
                isFullScreen = true,
                modifier = modifier,
                onBackPressed = onDetailScreenBackPressed
            )
        }
    }


}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationDrawerContent(
    selectedCategory: PlaceCategory,
    onTabPressed: ((PlaceCategory) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .wrapContentWidth()
            .fillMaxHeight()
//            .background(MaterialTheme.colorscheme.inverseOnSurface)
            .padding(12.dp)
    ) {
        NavigationDrawerHeader(modifier)
        for (navItem in NavigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedCategory == navItem.category,
                label = {
                    Text(
                        text = stringResource(id =  navItem.textId),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(painter = painterResource(id = navItem.icon),
                        contentDescription = stringResource(id = navItem.textId))
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.category) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyCityLogo()
        MyCityProfileImage(
            drawableResource = R.drawable.ic_baseline_location_city_24,
//            description = stringResource(id = "MyCity"),
            description = "MyCity",
            modifier = Modifier
                .size(28.dp)
        )
    }
}

@Composable
fun CityBottomNavigationBar(
    currentTab: PlaceCategory,
    onTabPressed: ((PlaceCategory) -> Unit),
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        for (navItem in NavigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.category,
                onClick = { onTabPressed(navItem.category) },
                icon = {
                    Icon(painter = painterResource(id = navItem.icon),
                        contentDescription = stringResource(id = navItem.textId))
                }
            )
        }
    }
}


@Composable
fun CityNavigationRail(
    currentTab: PlaceCategory,
    onTabPressed: ((PlaceCategory) -> Unit),
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier.fillMaxHeight()) {
        for (navItem in NavigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.category,
                onClick = { onTabPressed(navItem.category) },
                icon = {
                    Icon(painter = painterResource(id = navItem.icon),
                        contentDescription = stringResource(id = navItem.textId))
                }
            )
        }
    }
}



@Composable
fun MyCityLogo(modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(painter = painterResource(R.drawable.ic_baseline_location_city_24)
        , contentDescription = "My City"//stringResource(R.string.logo)
       , colorFilter = ColorFilter.tint(color)
        , modifier = modifier
    )
}


@Composable
fun CityHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        MyCityLogo(
            modifier = Modifier
                .size(64.dp)
                .padding(start = 4.dp)
        )
        MyCityProfileImage(
            drawableResource = R.drawable.ic_baseline_location_city_24,  // TODO:
            description = "MyCity", // TODO:
            modifier = Modifier
                .padding(end = 8.dp)
                .size(48.dp)
        )
    }
}

@Composable
fun MyCityProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painterResource(drawableResource),
        contentDescription = description,
    )
}



@Preview(showBackground = true)
@Composable
fun NavigationDrawerContentPreview() {
    MyCityTheme {
        NavigationDrawerContent(selectedCategory = PlaceCategory.Park
            , onTabPressed = {}
            , navigationItemContentList = NavigationItemContentList
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerHeaderPreview() {
    MyCityTheme {
        NavigationDrawerHeader(Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MyCityTheme {
        CityBottomNavigationBar(currentTab = PlaceCategory.CoffeeShop
                , onTabPressed = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CityNavigationRailPreview() {
    MyCityTheme {
        CityNavigationRail(currentTab = PlaceCategory.ShoppingMall
            , onTabPressed = {}
        )
    }
}