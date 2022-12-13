package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

data class DessertUiState (
    val revenue:Int = 0,
    val dessertsSold:Int = 0,
    //val currentDessertIndex:Int = 0,
)
