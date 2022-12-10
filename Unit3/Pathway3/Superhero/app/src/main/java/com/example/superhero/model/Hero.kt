package com.example.superhero.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
