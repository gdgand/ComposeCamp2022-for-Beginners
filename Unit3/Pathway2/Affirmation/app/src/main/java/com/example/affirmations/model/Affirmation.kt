package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(

    @StringRes val stringResourceId: Int,  //격언
    @DrawableRes val imageResourceId: Int  //이미지


)
