package com.example.coursesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicTitle: Int,
    val courseCount: Int,
    @DrawableRes val topicImage: Int
) {
    val courseCountString = courseCount.toString()
}
