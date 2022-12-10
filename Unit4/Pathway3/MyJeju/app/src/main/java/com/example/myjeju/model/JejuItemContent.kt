package com.example.myjeju.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class JejuItemContent(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val categoryResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val iconResourceId: Int,
    @StringRes val descriptionResourceId: Int
)
