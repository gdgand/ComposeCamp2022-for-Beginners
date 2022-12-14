package com.example.artspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtWork(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val artistId: Int
)