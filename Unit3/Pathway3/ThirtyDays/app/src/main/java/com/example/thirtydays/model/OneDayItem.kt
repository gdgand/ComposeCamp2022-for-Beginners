package com.example.thirtydays.model

import androidx.annotation.StringRes
import com.example.thirtydays.R

class OneDayItem(val day: Int
    , @StringRes val titleRes: Int
//    , val imageRes: Int
    , @StringRes val descriptionRes: Int
) {
}

object ThirtyDaysItemList {
    val items = listOf(
        OneDayItem(1, R.string.item_1, R.string.desc_1)
        , OneDayItem(2, R.string.item_2, R.string.desc_2)
        , OneDayItem(3, R.string.item_3, R.string.desc_3)
        , OneDayItem(4, R.string.item_4, R.string.desc_4)
        , OneDayItem(5, R.string.item_5, R.string.desc_5)
        , OneDayItem(6, R.string.item_6, R.string.desc_6)
    )
}