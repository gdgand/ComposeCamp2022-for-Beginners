package com.example.mycity

import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType


data class CityUiState(
    val places: Map<PlaceCategory, List<PlaceType>> = emptyMap(),
    val currentCategory: PlaceCategory = PlaceCategory.Restaurant,
    val selectedPlace: PlaceType? = null,
    val isShowingHome: Boolean = true
) {
    val selectedCategoryPlaces: List<PlaceType> by lazy { places[currentCategory]!! }
}