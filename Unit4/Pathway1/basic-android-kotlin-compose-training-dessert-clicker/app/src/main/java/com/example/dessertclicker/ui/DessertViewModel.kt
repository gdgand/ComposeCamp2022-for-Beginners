package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState = _uiState.asStateFlow()

    init {
        resetDesserts()
    }

    fun updateRevenue() {
        _uiState.update { currentState ->
            val nextIndex = determineNextIndex(dessertsSold = currentState.dessertsSold)
            currentState.copy(
                revenue = currentState.revenue + currentState.currentPrice,
                dessertsSold = currentState.dessertsSold + 1,
                currentIndex = nextIndex,
                currentPrice = dessertList[nextIndex].price,
                currentImageId = dessertList[nextIndex].imageId
            )
        }
    }

    private fun determineNextIndex(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0

        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertIndex

    }

    private fun resetDesserts() {
        _uiState.value = DessertUiState()
    }
}