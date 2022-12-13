package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUiState (
    val currentDessertIndex:Int = 0,
    val revenue:Int = 0,
    val dessertsSold:Int = 0,
    var currentDessertImageId:Int = dessertList[currentDessertIndex].imageId,
    var currentDessertPrice:Int = dessertList[currentDessertIndex].price,
)
