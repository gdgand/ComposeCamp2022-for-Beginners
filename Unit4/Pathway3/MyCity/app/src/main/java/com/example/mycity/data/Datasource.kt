package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Dessert

object Datasource {
    val dessertList = listOf<Dessert>(
        Dessert(R.drawable.cupcake, 5, R.string.dessert_description),
        Dessert(R.drawable.donut, 10, R.string.dessert_description),
        Dessert(R.drawable.eclair, 15, R.string.dessert_description),
        Dessert(R.drawable.froyo, 30, R.string.dessert_description),
        Dessert(R.drawable.gingerbread, 50, R.string.dessert_description),
        Dessert(R.drawable.honeycomb, 100, R.string.dessert_description),
        Dessert(R.drawable.icecreamsandwich, 500, R.string.dessert_description),
        Dessert(R.drawable.jellybean, 1000, R.string.dessert_description),
        Dessert(R.drawable.kitkat, 2000, R.string.dessert_description),
        Dessert(R.drawable.lollipop, 3000, R.string.dessert_description),
        Dessert(R.drawable.marshmallow, 4000, R.string.dessert_description),
        Dessert(R.drawable.nougat, 5000, R.string.dessert_description),
        Dessert(R.drawable.oreo, 6000, R.string.dessert_description),
    )


}
