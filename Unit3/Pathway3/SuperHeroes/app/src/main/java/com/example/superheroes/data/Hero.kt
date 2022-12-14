package com.example.superheroes.data

import androidx.annotation.StringRes
import androidx.annotation.DrawableRes

data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
