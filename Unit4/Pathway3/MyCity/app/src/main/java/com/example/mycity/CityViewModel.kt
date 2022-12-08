package com.example.mycity

import androidx.lifecycle.ViewModel

import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import com.example.mycity.data.local.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUIState()

    }

    fun updateCurrentCategory(category: PlaceCategory) {
        _uiState.update {
            it.copy(currentCategory = category)
        }
    }

    fun updatePlace(place: PlaceType) {
        _uiState.update {
            it.copy(selectedPlace = place, isShowingHome = false)
        }
    }

    // 무조건 해당 카테고리의 첫번째 아이템을 가리키도록
    fun resetHomeScreenState() {
        _uiState.update {
            it.copy(selectedPlace = it.selectedCategoryPlaces.get(0)
//            it.places[it.currentCategory].get(0)
                , isShowingHome = true
            )
        }
    }

    private fun initializeUIState() {
        var placeMapping: Map<PlaceCategory, List<PlaceType>> =
            Datasource.places.groupBy { it.category }
        _uiState.value =
            CityUiState(
                places = placeMapping,
                selectedPlace = placeMapping[PlaceCategory.Restaurant]!!.get(0) // 디풀트값
            )
    }
}