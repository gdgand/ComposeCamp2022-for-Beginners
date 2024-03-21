package com.example.mycity

import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType


data class CityUiState(
    val places: Map<PlaceCategory, List<PlaceType>> = emptyMap(),
    val currentCategory: PlaceCategory = PlaceCategory.ShoppingMall,
    val selectedPlace: PlaceType = PlaceType(title = ""
        , id = 0, category = PlaceCategory.CoffeeShop
        , address = "", tel = "", Email = "", like = 0, descriptions = ""),
    val currentScreen: MyCityScreen = MyCityScreen.Start
) {

    val selectedCategoryPlaces: List<PlaceType> by lazy { places[currentCategory]!! }
}