package com.example.artspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ImageRes(
    @DrawableRes val imageId: Int,
    val title: String,
    val subTitle: String
)
