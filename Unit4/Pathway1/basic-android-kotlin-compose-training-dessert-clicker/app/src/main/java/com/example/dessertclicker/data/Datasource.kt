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
package com.example.dessertclicker.data

import com.example.dessertclicker.R
import com.example.dessertclicker.model.Dessert

/**
 * [Datasource] generates a list of [Dessert]
 */
object Datasource {
    val dessertList = listOf(
        Dessert(R.drawable.cupcake, 5, 1),
        Dessert(R.drawable.donut, 10, 4),
        Dessert(R.drawable.eclair, 15, 7),
        Dessert(R.drawable.froyo, 30, 10),
        Dessert(R.drawable.gingerbread, 50, 12),
        Dessert(R.drawable.honeycomb, 100, 14),
        Dessert(R.drawable.icecreamsandwich, 500, 16),
        Dessert(R.drawable.jellybean, 1000, 18),
        Dessert(R.drawable.kitkat, 2000, 20),
        Dessert(R.drawable.lollipop, 3000, 22),
        Dessert(R.drawable.marshmallow, 4000, 23),
        Dessert(R.drawable.nougat, 5000, 27),
        Dessert(R.drawable.oreo, 6000, 29)
    )
}