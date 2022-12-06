package com.example.dessertclicker.ui

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.properties.Delegates

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    var uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    private var currentDessertPrice by Delegates.notNull<Int>()
    private var currentDessertImageId by Delegates.notNull<Int>()

    init {
        resetDessertClicker()
        changeDessert(Datasource.dessertList[_uiState.value.currentDessertIndex])
    }

    fun updateRevenue() {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentDessertPrice,
                dessertsSold = currentState.dessertsSold + 1
            )
        }
    }


    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow() {
        if (_uiState.value.currentDessertIndex != Datasource.dessertList.size - 1) {
            if (_uiState.value.revenue >= Datasource.dessertList[_uiState.value.currentDessertIndex + 1].startProductionAmount) {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentDessertIndex = currentState.currentDessertIndex + 1
                    )
                }
                changeDessert(Datasource.dessertList[_uiState.value.currentDessertIndex])
            }
        }
    }

    private fun changeDessert(dessert: Dessert) {
        currentDessertPrice = dessert.price
        currentDessertImageId = dessert.imageId
    }

    private fun resetDessertClicker() {
        _uiState.value = DessertUiState(revenue = 0, dessertsSold = 0, currentDessertIndex = 0)
    }
}