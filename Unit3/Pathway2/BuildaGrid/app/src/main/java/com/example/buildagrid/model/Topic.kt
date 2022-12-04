package com.example.buildagrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(@StringRes val stringRes: Int, val hit: Int, @DrawableRes val image: Int)
