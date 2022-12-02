package com.example.dessertclicker

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert

data class AppUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
//    val desserts: List<Dessert> = dessertList,
    val currentDessertPrice: Int = 0,
    @DrawableRes val currentDessertImageId: Int = 0
)
