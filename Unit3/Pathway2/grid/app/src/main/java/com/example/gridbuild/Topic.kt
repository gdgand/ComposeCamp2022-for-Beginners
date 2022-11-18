package com.example.gridbuild

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val course: Int,
    @DrawableRes val image: Int
)
