package com.example.artspace

import androidx.annotation.DrawableRes

enum class Pictures(@DrawableRes val drawableRes: Int, val title: String, val author: String) {
    PICTURE_1(R.drawable.the_arms_merchant, "The Arms Merchant", "Rudolf Ernst"),
    PICTURE_2(
        R.drawable.returning_from_the_fields,
        "Returning From the Fields",
        "William Edward Millner"
    ),
    PICTURE_3(R.drawable.kompositsioon, "Kompositsioon", "Lola Liivat"),
    PICTURE_4(
        R.drawable.view_from_the_town_hall_tower,
        "View from the town hall tower",
        "Jan Matejko"
    )
}