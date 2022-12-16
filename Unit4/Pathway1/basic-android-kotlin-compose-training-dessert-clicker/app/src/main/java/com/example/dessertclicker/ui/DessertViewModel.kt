package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()
    val desserts:List<Dessert> = dessertList

    fun updateUiState() {
        val nextDessertIndex = determineDessertToShow()
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = currentState.revenue.plus(currentState.currentDessertPrice),
                dessertsSold = currentState.dessertsSold.inc(),
                currentDessertImageId = desserts[nextDessertIndex].imageId,
                currentDessertPrice = desserts[nextDessertIndex].price
            )
        }
    }

    fun determineDessertToShow(
    ): Int {
        var dessertToShow = 0
        for (idx in desserts.indices) {
            if (_uiState.value.dessertsSold >= desserts[idx].startProductionAmount) {
                dessertToShow = idx
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