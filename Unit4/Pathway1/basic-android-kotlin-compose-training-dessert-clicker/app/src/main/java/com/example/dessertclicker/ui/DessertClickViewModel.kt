package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickUiState())
    val uiState: StateFlow<DessertClickUiState> = _uiState.asStateFlow()


    fun onDessertClick() {
        val dessertsSold = _uiState.value.dessertsSold + 1
        val nextDessertIndex = determineDessertIndex(dessertsSold)

        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = currentState.dessertsSold.inc(),
                currentDessertIndex = nextDessertIndex,
                currentDessertPrice = dessertList[nextDessertIndex].price,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
            )
        }
    }

    private fun determineDessertIndex(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0

        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // 현재 팔리고 있는 디저트의 경우
                break
            }
        }

        return dessertIndex
    }

}