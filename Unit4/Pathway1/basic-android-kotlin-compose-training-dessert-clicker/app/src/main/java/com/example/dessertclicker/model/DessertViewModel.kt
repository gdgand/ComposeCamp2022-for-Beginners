/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dessertclicker.model

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.Dessert
import com.example.dessertclicker.ui.theme.DessertClickerTheme


private const val TAG = "DessertViewModel"

class DessertViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    var uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()
    private val desserts = Datasource.dessertList

    init {
//        _uiState.value = DessertUiState(...)
    }

    fun getCurrentDessertIndex() = uiState.value.currentDessertIndex


    fun onDessertClicked() {
        // Update the revenue
        updateSoldAndRevenue()
        // Show the next dessert
        val  dessertToShow = determineDessertToShow(_uiState.value.dessertsSold)
        val  indexOfDessertToShow = desserts.indexOf(element = dessertToShow)
        setNewDessert(dessert = dessertToShow
            , index = indexOfDessertToShow)
    }


    /**
     * Determine which dessert to show.
     */
   private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
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


    private fun updateSoldAndRevenue() {
        _uiState.update {
            it.copy(
                revenue = _uiState.value.revenue.plus(_uiState.value.currentDessertPrice)
                , dessertsSold = _uiState.value.dessertsSold.inc()
            )
        }
        Log.d(TAG, "uiState = ${_uiState.value.currentDessertIndex}"
                + ", revenue = ${_uiState.value.currentDessertPrice}"
                + ", dessertsSold = ${_uiState.value.dessertsSold}")
    }

    private fun setNewDessert(dessert: Dessert, index: Int) {
        _uiState.update { it ->
            it.copy(
                currentDessertPrice = dessert.price,
                currentDessertImageId = dessert.imageId,
                currentDessertIndex = index
            )
        }
        Log.d(TAG, "dessert.index = $index, $dessert, " +
                "imageId: ${_uiState.value.currentDessertImageId} "
                + "price: ${_uiState.value.currentDessertPrice}")
    }
}

data class DessertUiState(
    val revenue: Int = 0
    , val dessertsSold: Int = 0  // 팔린 갯수 카운트
    , val currentDessertIndex: Int = 0
    , val currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price
    , val currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)
