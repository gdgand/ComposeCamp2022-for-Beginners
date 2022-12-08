package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.example.dessertclicker.R

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = 5,
    @DrawableRes val currentDessertImageId: Int = R.drawable.cupcake,
    )
