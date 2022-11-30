package com.example.dessertclicker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.ui.state.DessertClickerUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel(){

    private val _dessertClickerUIState = MutableStateFlow(DessertClickerUIState())
    val dessertClickerUIState : StateFlow<DessertClickerUIState>  = _dessertClickerUIState.asStateFlow()



    fun onDessertClicked(){
        // Update the revenue
        _dessertClickerUIState.update { it ->
            val dessertsSold = it.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            it.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = it.revenue + it.currentDessertPrice,
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
