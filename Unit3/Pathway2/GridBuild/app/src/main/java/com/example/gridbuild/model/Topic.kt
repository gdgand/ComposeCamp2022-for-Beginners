package com.example.gridbuild.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResId: Int,
    val captionValue: Int,
    @DrawableRes val drawableResId: Int
)
