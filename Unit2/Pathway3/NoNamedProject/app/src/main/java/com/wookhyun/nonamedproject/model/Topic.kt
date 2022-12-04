package com.wookhyun.nonamedproject.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Topic(
    @StringRes val title: Int,
    val count: Int,
    @DrawableRes val imageResourceId: Int,
)