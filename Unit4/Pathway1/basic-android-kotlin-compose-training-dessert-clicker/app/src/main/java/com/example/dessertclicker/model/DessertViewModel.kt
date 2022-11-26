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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "DessertViewModel"

class DessertViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

//    fun getCurrentDessertIndex() = uiState.value.currentDessertIndex

    fun updateSoldAndRevenue() {
        _uiState.update {
            it.copy(
                revenue = _uiState.value.revenue.plus(_uiState.value.currentDessertPrice)
                , dessertsSold = _uiState.value.dessertsSold.inc()
            )
        }
    }
    fun setNewDessert(dessert: Dessert) {
        _uiState.update { it ->
            it.copy(
                currentDessertPrice = dessert.price,
                currentDessertImageId = dessert.imageId
            )
        }
    }

}

data class DessertUiState(
    val revenue: Int = 0
    , val dessertsSold: Int = 0  // 팔린 갯수 카운트
//    , val currentDessertIndex: Int = 0
    , val currentDessertPrice: Int = 0
    , val currentDessertImageId: Int = 0
)

/**
 * [Dessert] is the data class to represent the Dessert imageId, price, and startProductionAmount
 */
data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int) {

}