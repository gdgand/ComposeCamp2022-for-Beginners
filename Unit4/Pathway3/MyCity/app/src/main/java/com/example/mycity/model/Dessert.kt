package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dessert(
    @DrawableRes val imageId: Int,
    @StringRes val name: Int,
    val price: Int,
    @StringRes val description: Int,
)
