package com.example.a30days.model

import androidx.annotation.StringRes
import com.example.a30days.R

data class Exercise(
    val day: String,
    @StringRes val source: Int,
    @StringRes val meaning: Int,
    val exercise: String,
    @StringRes val pronounce: Int
)

val Exercises = listOf(
    Exercise("Day1", R.string.source01, R.string.mean01, "바벨을 어깨위로", R.string.pronounce01),
    Exercise("Day2", R.string.source02, R.string.mean02, "덤벨을 위아래로", R.string.pronounce02),
    Exercise("Day3", R.string.source03, R.string.mean03, "덤벨을 위 아래로", R.string.pronounce03),
    Exercise("Day4", R.string.source04, R.string.mean04, "누워서 바벨을 위 아래로", R.string.pronounce04),
    Exercise("Day5", R.string.source05, R.string.mean05, "바벨을 누워서 위 아래로", R.string.pronounce05),
    Exercise("Day6", R.string.source06, R.string.mean06, "엎드려서 버티기", R.string.pronounce06),
    Exercise("Day7", R.string.source07, R.string.mean07, "쪼그리기", R.string.pronounce07),
)