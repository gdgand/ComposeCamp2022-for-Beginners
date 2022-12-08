package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _dessertUiState: MutableStateFlow<DessertUiState> =
        MutableStateFlow(DessertUiState())
    val dessertUiState: StateFlow<DessertUiState> = _dessertUiState.asStateFlow()

    private fun revenuePlus(price: Int) {
        _dessertUiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + price
            )
        }
    }

    private fun dessertsSoldPlus() {
        _dessertUiState.update { currentState ->
            currentState.copy(
                dessertsSold = currentState.dessertsSold + 1
            )
        }
    }

    fun dessertClick(price: Int) {
        revenuePlus(price)
        dessertsSoldPlus()
        val dessertToShow = determineDessertToShow(dessertList, _dessertUiState.value.dessertsSold)
        _dessertUiState.update { currentState ->
            currentState.copy(
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
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