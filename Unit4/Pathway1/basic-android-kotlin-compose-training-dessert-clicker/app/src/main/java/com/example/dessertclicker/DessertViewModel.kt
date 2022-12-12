package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClick() {
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val indexToShow = determineDessertIndexToShow(dessertList, dessertsSold)

            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = currentState.dessertsSold + 1,
                currentDessertIndex = indexToShow,
                currentDessertImageId = dessertList[indexToShow].imageId,
                currentDessertPrice = dessertList[indexToShow].price
            )
        }
    }

    private fun determineDessertIndexToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Int {
        var dessertIndexToShow = 0
        for (index in desserts.indices) {
            if (dessertsSold >= desserts[index].startProductionAmount) {
                dessertIndexToShow = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return dessertIndexToShow
    }
}