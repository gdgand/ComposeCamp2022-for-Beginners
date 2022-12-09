package com.example.myjeju.data

import com.example.myjeju.R
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.model.JejuItemContent

object JejuDataProvider {
    val defaultJejuData = getJejuData()[0]

    fun getCategoryData(currentCategoryItem: CategoryItemContent): List<JejuItemContent> {
        return getJejuData().filter {
            it.categoryResourceId == currentCategoryItem.categoryResourceId
        }
    }

    fun getCategory(): List<CategoryItemContent> {
        return listOf(
            CategoryItemContent(
                categoryResourceId = R.string.c_beach,
                iconResourceId = R.drawable.icon_beach
            ),
            CategoryItemContent(
                categoryResourceId = R.string.c_island,
                iconResourceId = R.drawable.icon_island
            ),
            CategoryItemContent(
                categoryResourceId = R.string.c_mountain,
                iconResourceId = R.drawable.icon_mountain
            ),
            CategoryItemContent(
                categoryResourceId = R.string.c_market,
                iconResourceId = R.drawable.icon_market
            ),
        )
    }

    fun getJejuData(): List<JejuItemContent> {
        return listOf(
            JejuItemContent(
                id = 1,
                titleResourceId = R.string.b_hamduckbeach,
                categoryResourceId = R.string.c_beach,
                imageResourceId = R.drawable.b_hamduckbeach,
                iconResourceId = R.drawable.icon_beach,
                descriptionResourceId = R.string.b_hamduckbeach_desc
            ),
            JejuItemContent(
                id = 2,
                titleResourceId = R.string.b_hypjaebeach,
                categoryResourceId = R.string.c_beach,
                imageResourceId = R.drawable.b_hypjaebeach,
                iconResourceId = R.drawable.icon_beach,
                descriptionResourceId = R.string.b_hypjaebeach_desc
            ),
            JejuItemContent(
                id = 3,
                titleResourceId = R.string.b_jungmunbeach,
                categoryResourceId = R.string.c_beach,
                imageResourceId = R.drawable.b_jungmunbeach,
                iconResourceId = R.drawable.icon_beach,
                descriptionResourceId = R.string.b_jungmunbeach_desc
            ),
            JejuItemContent(
                id = 4,
                titleResourceId = R.string.b_pyosonbeach,
                categoryResourceId = R.string.c_beach,
                imageResourceId = R.drawable.b_pyosonbeach,
                iconResourceId = R.drawable.icon_beach,
                descriptionResourceId = R.string.b_pyosonbeach_desc
            ),
            JejuItemContent(
                id = 5,
                titleResourceId = R.string.i_biyangdo,
                categoryResourceId = R.string.c_island,
                imageResourceId = R.drawable.i_biyangdo,
                iconResourceId = R.drawable.icon_island,
                descriptionResourceId = R.string.i_biyangdo_desc
            ),
            JejuItemContent(
                id = 6,
                titleResourceId = R.string.i_marado,
                categoryResourceId = R.string.c_island,
                imageResourceId = R.drawable.i_marado,
                iconResourceId = R.drawable.icon_island,
                descriptionResourceId = R.string.i_marado_desc
            ),
            JejuItemContent(
                id = 7,
                titleResourceId = R.string.i_wudo,
                categoryResourceId = R.string.c_island,
                imageResourceId = R.drawable.i_wudo,
                iconResourceId = R.drawable.icon_island,
                descriptionResourceId = R.string.i_wudo_desc
            ),
            JejuItemContent(
                id = 8,
                titleResourceId = R.string.m_gumunorum,
                categoryResourceId = R.string.c_mountain,
                imageResourceId = R.drawable.m_gumunorum,
                iconResourceId = R.drawable.icon_mountain,
                descriptionResourceId = R.string.m_gumunorum_desc
            ),
            JejuItemContent(
                id = 9,
                titleResourceId = R.string.m_hallasan,
                categoryResourceId = R.string.c_mountain,
                imageResourceId = R.drawable.m_hallasan,
                iconResourceId = R.drawable.icon_mountain,
                descriptionResourceId = R.string.m_hallasan_desc
            ),
            JejuItemContent(
                id = 10,
                titleResourceId = R.string.m_sanbangsan,
                categoryResourceId = R.string.c_mountain,
                imageResourceId = R.drawable.m_sanbangsan,
                iconResourceId = R.drawable.icon_mountain,
                descriptionResourceId = R.string.m_sanbangsan_desc
            ),
            JejuItemContent(
                id = 11,
                titleResourceId = R.string.mk_5daymarket,
                categoryResourceId = R.string.c_market,
                imageResourceId = R.drawable.mk_5daymarket,
                iconResourceId = R.drawable.icon_market,
                descriptionResourceId = R.string.mk_5daymarket_desc
            ),
            JejuItemContent(
                id = 12,
                titleResourceId = R.string.mk_dongmunmarket,
                categoryResourceId = R.string.c_market,
                imageResourceId = R.drawable.mk_dongmunmarket,
                iconResourceId = R.drawable.icon_market,
                descriptionResourceId = R.string.mk_dongmunmarket_desc
            ),
            JejuItemContent(
                id = 13,
                titleResourceId = R.string.mk_seogwipomaeilollemarket,
                categoryResourceId = R.string.c_market,
                imageResourceId = R.drawable.mk_seogwipomaeilollemarket,
                iconResourceId = R.drawable.icon_market,
                descriptionResourceId = R.string.mk_seogwipomaeilollemarket_desc
            )
        )
    }
}