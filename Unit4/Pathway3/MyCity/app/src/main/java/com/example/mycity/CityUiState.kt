package com.example.mycity

import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType


data class CityUiState(
    val places: Map<PlaceCategory, List<PlaceType>> = emptyMap(),
    val currentCategory: PlaceCategory = PlaceCategory.Restaurant,
    val selectedPlace: PlaceType = PlaceType(title = ""
        , id = 0, category = PlaceCategory.CoffeeShop
        , address = "", tel = "", Email = "", like = 0, descriptions = ""),
    val isShowingHome: Boolean = true
) {

    val selectedCategoryPlaces: List<PlaceType> by lazy { places[currentCategory]!! }
}