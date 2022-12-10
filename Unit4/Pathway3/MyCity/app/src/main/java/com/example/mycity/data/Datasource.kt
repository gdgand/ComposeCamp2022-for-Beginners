package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Category
import com.example.mycity.model.Dessert
import com.example.mycity.model.Sport

object Datasource {
    val categoryList = listOf<Category>(
        Category(R.drawable.cupcake, R.string.category_dessert),
        Category(R.drawable.ic_baseball_square, R.string.category_sport),
    )

    val dessertList = listOf<Dessert>(
        Dessert(R.drawable.cupcake, R.string.cupcake, 5, R.string.dessert_description),
        Dessert(R.drawable.donut, R.string.donut,10, R.string.dessert_description),
        Dessert(R.drawable.eclair, R.string.eclair, 15, R.string.dessert_description),
        Dessert(R.drawable.froyo, R.string.froyo,  30, R.string.dessert_description),
        Dessert(R.drawable.gingerbread, R.string.gingerbread,  50, R.string.dessert_description),
        Dessert(R.drawable.honeycomb, R.string.honeycomb,  100, R.string.dessert_description),
        Dessert(R.drawable.icecreamsandwich, R.string.icecreamsandwich,  500, R.string.dessert_description),
        Dessert(R.drawable.jellybean, R.string.jellybean,  1000, R.string.dessert_description),
        Dessert(R.drawable.kitkat, R.string.kitkat,  2000, R.string.dessert_description),
        Dessert(R.drawable.lollipop, R.string.lollipop,  3000, R.string.dessert_description),
        Dessert(R.drawable.marshmallow, R.string.marshmallow,  4000, R.string.dessert_description),
        Dessert(R.drawable.nougat, R.string.nougat,  5000, R.string.dessert_description),
        Dessert(R.drawable.oreo, R.string.oreo,  6000, R.string.dessert_description),
    )

    val SportList = listOf<Sport>(
        Sport(
            id = 1,
            titleResourceId = R.string.baseball,
            imageResourceId = R.drawable.ic_baseball_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 2,
            titleResourceId = R.string.badminton,
            imageResourceId = R.drawable.ic_badminton_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 3,
            titleResourceId = R.string.basketball,
            imageResourceId = R.drawable.ic_basketball_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 4,
            titleResourceId = R.string.bowling,
            imageResourceId = R.drawable.ic_bowling_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 5,
            titleResourceId = R.string.cycling,
            imageResourceId = R.drawable.ic_cycling_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 6,
            titleResourceId = R.string.golf,
            imageResourceId = R.drawable.ic_golf_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 7,
            titleResourceId = R.string.running,
            imageResourceId = R.drawable.ic_running_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 8,
            titleResourceId = R.string.soccer,
            imageResourceId = R.drawable.ic_soccer_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 9,
            titleResourceId = R.string.swimming,
            imageResourceId = R.drawable.ic_swimming_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 10,
            titleResourceId = R.string.table_tennis,
            imageResourceId = R.drawable.ic_table_tennis_square,
            newsDetails = R.string.sports_news_detail_text
        ),
        Sport(
            id = 11,
            titleResourceId = R.string.tennis,
            imageResourceId = R.drawable.ic_tennis_square,
            newsDetails = R.string.sports_news_detail_text
        )
    )


}
