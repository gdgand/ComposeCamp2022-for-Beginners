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
            it.copy(currentCategory = category
                , currentScreen = MyCityScreen.List
            )
        }
        // TODO: Medium, Expanded 에서, selectedCategoryPlaces 가 새 category로 한박자 늦게 반영됨.
        //  Reply 예제에서는 Default(고정값)를 뿌리고 끝.
        _uiState.update {
            it.copy(selectedPlace = it.selectedCategoryPlaces.get(0))
        }
    }

    fun updatePlace(place: PlaceType) {
        _uiState.update {
            it.copy(selectedPlace = place, currentScreen = MyCityScreen.Detail
            )
        }
    }

    // 무조건 해당 카테고리의 첫번째 아이템을 가리키도록
    fun resetDetailScreenState() {
        _uiState.update {
            it.copy(selectedPlace = it.selectedCategoryPlaces.get(0)
//            it.places[it.currentCategory].get(0)
                , currentScreen = MyCityScreen.List
            )
        }
    }

    fun resetListScreenState() {
        _uiState.update {
            it.copy(selectedPlace = it.selectedCategoryPlaces.get(0)
                , currentScreen = MyCityScreen.Start
            )
        }
    }

    private fun initializeUIState() {
        var placeMapping: Map<PlaceCategory, List<PlaceType>> =
            Datasource.places.groupBy { it.category }
        _uiState.value =
            CityUiState(
                places = placeMapping
                , selectedPlace = placeMapping[PlaceCategory.Restaurant]!!.get(0) // 디풀트값
                , currentScreen = MyCityScreen.Start
            )
    }
}