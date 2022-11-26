package com.example.coursesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes
    val stringResource: Int,
    val caption: Int,
    @DrawableRes
    val painterResource: Int
)
