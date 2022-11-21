package com.compose.camp.hun.artspace.model

import androidx.annotation.DrawableRes
import com.compose.camp.hun.artspace.R

data class ArtModel(@DrawableRes val image: Int, val title: String, val desc: String, val year: Int)

val artList = listOf<ArtModel>(
    ArtModel(R.drawable.first, "first dog", "my little dog", 2022),
    ArtModel(R.drawable.second, "second dog", "my little dog second", 2021),
    ArtModel(R.drawable.third, "third dog", "my little dog third", 2022),
    ArtModel(R.drawable.fourth, "fourth dog", "my little dog fourth", 2021),
    ArtModel(R.drawable.fifth, "fifth dog", "my little dog fifth", 2022),
    ArtModel(R.drawable.sixth, "sixth dog", "my little dog sixth", 2021),
)