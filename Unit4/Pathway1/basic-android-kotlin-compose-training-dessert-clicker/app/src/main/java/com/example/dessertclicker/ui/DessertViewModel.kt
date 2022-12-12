package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _dessertUIState = MutableStateFlow(DessertUIState())
    val dessertUIState: StateFlow<DessertUIState> = _dessertUIState.asStateFlow()

    fun onDessertClicked() {
        _dessertUIState.update { cupcakeUIState ->
            val dessertsSold = cupcakeUIState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            cupcakeUIState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = cupcakeUIState.revenue + cupcakeUIState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
                currentDessertPrice = dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }
}