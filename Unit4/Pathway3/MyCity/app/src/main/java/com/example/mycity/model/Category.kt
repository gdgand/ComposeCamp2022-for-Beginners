package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @DrawableRes val imageId: Int,
    @StringRes val name: Int,
)
