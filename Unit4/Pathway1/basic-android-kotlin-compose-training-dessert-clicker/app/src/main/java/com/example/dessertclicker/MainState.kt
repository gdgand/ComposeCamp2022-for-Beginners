package com.example.dessertclicker

import androidx.annotation.DrawableRes

data class MainState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    @DrawableRes val currentDessertImageId: Int = 0
)
