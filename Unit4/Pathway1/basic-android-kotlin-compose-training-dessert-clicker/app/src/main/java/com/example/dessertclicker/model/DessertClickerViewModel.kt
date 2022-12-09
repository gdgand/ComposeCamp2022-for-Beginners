package com.example.dessertclicker.model

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {

    private val _dcs: MutableStateFlow<DessertClickerState> =
        MutableStateFlow(DessertClickerState())
    val dcs = _dcs.asStateFlow()

    private var dessertToShow:Dessert

    init {
        dessertToShow = Datasource.dessertList[0]
    }

    fun getCurrentDessertPrice(): Int {
        return dessertToShow.price
    }

    fun getCurrentImageResourceId(): Int {
        return dessertToShow.imageId
    }

    fun clickDessert() {
        _dcs.update {
            it.copy(
                revenue = it.revenue + getCurrentDessertPrice(),
                dessertsSold = it.dessertsSold + 1,
            )
        }
        dessertToShow = determineDessertToShow()
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(): Dessert {
        val dessertsSold = _dcs.value.dessertsSold
        var dessertToShow = Datasource.dessertList.first()
        for (dessert in Datasource.dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
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