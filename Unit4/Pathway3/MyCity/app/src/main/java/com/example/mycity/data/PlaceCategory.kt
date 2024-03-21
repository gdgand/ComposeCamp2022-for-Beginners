package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.mycity.R

enum class PlaceCategory {
    CoffeeShop, Restaurant, Park, ShoppingMall
}


data class NavigationItemContent(
    val category: PlaceCategory,
    @DrawableRes val icon: Int,
    @StringRes val textId: Int
)

val  NavigationItemContentList = listOf(
    NavigationItemContent(
        category = PlaceCategory.Restaurant,
        icon = R.drawable.ic_restaurant,
        textId = R.string.tab_restaurant
    ),
    NavigationItemContent(
        category = PlaceCategory.CoffeeShop,
        icon = R.drawable.ic_coffeeshop,
        textId = R.string.tab_coffeeshop
    ),
    NavigationItemContent(
        category = PlaceCategory.Park,
        icon = R.drawable.ic_park,
        textId = R.string.tab_park
    ),
    NavigationItemContent(
        category = PlaceCategory.ShoppingMall,
        icon = R.drawable.ic_shoppingmall,
        textId = R.string.tab_shoppingmall
    )
)
