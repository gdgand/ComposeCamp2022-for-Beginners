package com.example.myjeju.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CategoryItemContent(
    @StringRes val categoryResourceId: Int,
    @DrawableRes val iconResourceId: Int,
)
