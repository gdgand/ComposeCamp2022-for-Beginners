package com.example.affirmationscodelab.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringRes: Int,
    val caption: Int,
    @DrawableRes val imageRes: Int
)
