package com.example.mycity

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CityHomeScreen(
//    navigationType: CityNavigationType,
//    contentType: CityContentType,
    uiState: CityUiState,
    onTabPressed: (PlaceCategory) -> Unit,
    onPlaceCardPressed: (PlaceType) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            category = PlaceCategory.Restaurant,
            icon = R.drawable.ic_restaurant,
            text = stringResource(id = R.string.tab_restaurant)
        ),
        NavigationItemContent(
            category = PlaceCategory.CoffeeShop,
            icon = R.drawable.ic_coffeeshop,
            text = stringResource(id = R.string.tab_coffeeshop)
        ),
        NavigationItemContent(
            category = PlaceCategory.Park,
            icon = R.drawable.ic_park,
            text = stringResource(id = R.string.tab_park)
        ),
        NavigationItemContent(
            category = PlaceCategory.ShoppingMall,
            icon = R.drawable.ic_shoppingmall,
            text = stringResource(id = R.string.tab_shoppingmall)
        )
    )


}




private data class NavigationItemContent(
    val category: PlaceCategory,
    @DrawableRes val icon: Int,
    val text: String
)


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
            .background(MaterialTheme.colorscheme.inverseOnSurface)
            .padding(12.dp)
    ) {
        NavigationDrawerHeader(modifier)
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedCategory == navItem.category,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.text)
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
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.userAccount.avatar,
            description = stringResource(id = R.string.profile),
            modifier = Modifier
                .size(28.dp)
        )
    }
}

@Composable
fun MyCityLogo(modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(painter = painterResource(R.drawable.ic_baseline_location_city_24)
        , contentDescription = "My City"//stringResource(R.string.logo)
//       , colorFilter = ColorFilter.tint(color)
        , modifier = modifier
    )
}

@Composable
fun ReplyProfileImage(
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
