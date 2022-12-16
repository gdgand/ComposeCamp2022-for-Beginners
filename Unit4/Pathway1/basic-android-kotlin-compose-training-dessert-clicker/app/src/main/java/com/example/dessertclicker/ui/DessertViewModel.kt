package com.example.dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()


    fun updateDessertState() {

    }

    fun soldDessert() {
        _uiState.update { currentState ->
            val dessertsSold = uiState.value.dessertsSold + 1
            val nextIndex = determineDessertToShow(dessertsSold)
            currentState.copy(
                revenue = uiState.value.revenue + uiState.value.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertIndex = nextIndex,
                currentDessertPrice = dessertList[nextIndex].price,
                currentDessertImageId = dessertList[nextIndex].imageId
            )
        }
    }

    fun determineDessertToShow(dessertsSold: Int): Int {
        var index = 0
        for (i in dessertList.indices) {
            if (dessertsSold >= dessertList[i].startProductionAmount) {
                index = i
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return index
    }
}