/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.woofcodelab.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirydaysofdogs.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Information(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    val day: Int,
    @StringRes val detail: Int
)

val infos = listOf(
    Information(R.drawable.koda, R.string.dog_name_1, 1, R.string.dog_description_1),
    Information(R.drawable.lola, R.string.dog_name_2, 2, R.string.dog_description_2),
    Information(R.drawable.frankie, R.string.dog_name_3, 3, R.string.dog_description_3),
    Information(R.drawable.nox, R.string.dog_name_4, 4, R.string.dog_description_4),
    Information(R.drawable.faye, R.string.dog_name_5, 5, R.string.dog_description_5),
    Information(R.drawable.bella, R.string.dog_name_6, 6, R.string.dog_description_6),
    Information(R.drawable.moana, R.string.dog_name_7, 7, R.string.dog_description_7),
    Information(R.drawable.tzeitel, R.string.dog_name_8, 8, R.string.dog_description_8),
    Information(R.drawable.leroy, R.string.dog_name_9, 9, R.string.dog_description_9),
    Information(R.drawable.koda, R.string.dog_name_1, 10, R.string.dog_description_1),
    Information(R.drawable.koda, R.string.dog_name_1, 11, R.string.dog_description_1),
    Information(R.drawable.lola, R.string.dog_name_2, 12, R.string.dog_description_2),
    Information(R.drawable.frankie, R.string.dog_name_3, 13, R.string.dog_description_3),
    Information(R.drawable.nox, R.string.dog_name_4, 14, R.string.dog_description_4),
    Information(R.drawable.faye, R.string.dog_name_5, 15, R.string.dog_description_5),
    Information(R.drawable.bella, R.string.dog_name_6, 16, R.string.dog_description_6),
    Information(R.drawable.moana, R.string.dog_name_7, 17, R.string.dog_description_7),
    Information(R.drawable.tzeitel, R.string.dog_name_8, 18, R.string.dog_description_8),
    Information(R.drawable.leroy, R.string.dog_name_9, 19, R.string.dog_description_9),
    Information(R.drawable.koda, R.string.dog_name_1, 20, R.string.dog_description_1),
    Information(R.drawable.koda, R.string.dog_name_1, 21, R.string.dog_description_1),
    Information(R.drawable.lola, R.string.dog_name_2, 22, R.string.dog_description_2),
    Information(R.drawable.frankie, R.string.dog_name_3, 23, R.string.dog_description_3),
    Information(R.drawable.nox, R.string.dog_name_4, 24, R.string.dog_description_4),
    Information(R.drawable.faye, R.string.dog_name_5, 25, R.string.dog_description_5),
    Information(R.drawable.bella, R.string.dog_name_6, 26, R.string.dog_description_6),
    Information(R.drawable.moana, R.string.dog_name_7, 27, R.string.dog_description_7),
    Information(R.drawable.tzeitel, R.string.dog_name_8, 28, R.string.dog_description_8),
    Information(R.drawable.leroy, R.string.dog_name_9, 29, R.string.dog_description_9),
    Information(R.drawable.koda, R.string.dog_name_1, 30, R.string.dog_description_1),
)