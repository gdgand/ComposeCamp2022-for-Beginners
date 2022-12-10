package com.example.seodang.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.seodang.R

data class Quote(
    val day: String,
    @StringRes val source: Int,
    val quote: String,
    @StringRes val meaning: Int,
    @StringRes val pronounce: Int
)

val quotes = listOf(
    Quote("一日", R.string.source01, "知之爲知之 不知爲不知 是知也", R.string.mean01, R.string.pronounce01),
    Quote("二日", R.string.source02, "愛人者 人恒愛之, 敬人者 人恒敬之", R.string.mean02, R.string.pronounce02),
    Quote("三日", R.string.source03, "莫見乎隱 莫顯乎微 故 君子愼其獨也", R.string.mean03, R.string.pronounce03),
    Quote("四日", R.string.source04, "上善若水 水善利萬物而不爭 處衆人之所惡", R.string.mean04, R.string.pronounce04),
    Quote("五日", R.string.source05, "子曰 君子 和而不同 小人 同而不和", R.string.mean05, R.string.pronounce05),
    Quote("六日", R.string.source06, "子曰 知者樂水 仁者樂山", R.string.mean06, R.string.pronounce06),
    Quote("七日", R.string.source07, "德不孤 必有隣", R.string.mean07, R.string.pronounce07),
)