package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val nowIndex = 0
    private var currentDessertPrice = dessertList[nowIndex].imageId

    private val _uiState =
        MutableStateFlow(MainState(currentDessertImageId = dessertList[nowIndex].imageId))
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    fun dessertAdd() {
        _uiState.update {
            it.copy(
                revenue = it.revenue + dessertList[nowIndex].price,
                dessertsSold = it.dessertsSold + 1
            )
        }

        determineDessertToShow(_uiState.value.dessertsSold).let {
            _uiState.update { state ->
                state.copy(
                    currentDessertImageId = it.imageId
                )
            }
            currentDessertPrice = it.price
        }
    }

    private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = dessertList.first()
        for (dessert in dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

}