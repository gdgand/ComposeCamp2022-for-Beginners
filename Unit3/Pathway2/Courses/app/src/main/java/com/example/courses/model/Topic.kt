package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val numOfCourses: Int,
    @DrawableRes val image: Int
)
