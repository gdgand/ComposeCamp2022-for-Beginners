package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel:ViewModel() {
    private val _dessertData = MutableStateFlow(DessertData())
    val dessertData:StateFlow<DessertData> = _dessertData.asStateFlow()

    fun onDessertClicked(){
        _dessertData.update {
            val dessertsSold = it.dessertsSold+1
            val nextDessertIdx = determineDessertToShow(dessertsSold)

            it.copy(
                currentDessertIndex = nextDessertIdx,
                revenue = it.revenue + it.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertList[nextDessertIdx].imageId,
                currentDessertPrice = dessertList[nextDessertIdx].price
            )
        }
    }

    private fun determineDessertToShow(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. We know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold.
                break
            }
        }
        return dessertIndex
    }
}