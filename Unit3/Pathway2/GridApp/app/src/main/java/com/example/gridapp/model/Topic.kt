package com.example.affirmationscodelab.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val count: Int,
    @DrawableRes val imageResourceId: Int
)
