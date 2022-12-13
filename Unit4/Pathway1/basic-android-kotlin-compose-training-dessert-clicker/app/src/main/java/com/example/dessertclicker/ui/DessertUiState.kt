package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUiState (
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentIndex: Int = 0,
    val currentPrice: Int = dessertList[currentIndex].price,
    @DrawableRes val currentImageId: Int = dessertList[currentIndex].imageId
)