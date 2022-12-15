package com.example.gridbuild.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResId: Int,
    val number: Int,
    @DrawableRes val drawableResId: Int
)
