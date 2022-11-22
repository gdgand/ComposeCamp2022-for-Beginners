package com.example.gridapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val caption: Int,
    @DrawableRes val imageResourceId: Int
)
