package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val desserts: List<Dessert> = Datasource.dessertList


    private val _uiState = MutableStateFlow(DessertState())
    val uiState: StateFlow<DessertState> = _uiState.asStateFlow()

    fun click(){
        _uiState.update {
            val nowRevenue = it.revenue + it.currentDessertPrice
            val nowDessertSold = it.dessertsSold + 1
            val nextIndex = determine(nowDessertSold)

            it.copy(
                revenue = nowRevenue,
                dessertsSold = nowDessertSold,
                currentDessertIndex = nextIndex,
                currentDessertImageId = desserts[nextIndex].imageId,
                currentDessertPrice = desserts[nextIndex].price
            )
        }
    }

    private fun determine(soldCount: Int): Int {
        var index = 0
        for (dessert in desserts.indices) {
            if (soldCount >= desserts[index].startProductionAmount) {
                index++
            } else {
                break
            }
        }

        return index
    }
}