package com.compose.camp.hun.mygridbuild.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(@StringRes val title: Int, val count: Int, @DrawableRes val imageRes: Int)
