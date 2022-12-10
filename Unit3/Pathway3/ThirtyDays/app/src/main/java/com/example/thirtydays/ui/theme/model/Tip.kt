package com.example.thirtydays.ui.theme.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val contentRes: Int,
)