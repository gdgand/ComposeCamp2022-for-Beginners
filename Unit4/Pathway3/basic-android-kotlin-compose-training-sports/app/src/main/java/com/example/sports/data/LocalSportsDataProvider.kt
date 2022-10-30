/*
 * Copyright (c) 2021 The Android Open Source Project
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

package com.example.sports.data

import com.example.sports.R
import com.example.sports.model.Sport

/**
 * Sports data
 */
object LocalSportsDataProvider{
    val defaultSport = getSportsData()[0]

    fun getSportsData(): List<Sport> {
        return listOf(
            Sport(
                id = 1,
                titleResourceId = R.string.baseball,
                subtitleResourceId = R.string.baseball_subtitle,
                imageResourceId = R.drawable.ic_baseball_square,
                sportsImageBanner = R.drawable.ic_baseball_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 2,
                titleResourceId = R.string.badminton,
                subtitleResourceId = R.string.badminton_subtitle,
                imageResourceId = R.drawable.ic_badminton_square,
                sportsImageBanner = R.drawable.ic_badminton_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 3,
                titleResourceId = R.string.basketball,
                subtitleResourceId = R.string.basketball_subtitle,
                imageResourceId = R.drawable.ic_basketball_square,
                sportsImageBanner = R.drawable.ic_basketball_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.bowling,
                subtitleResourceId = R.string.bowling_subtitle,
                imageResourceId = R.drawable.ic_bowling_square,
                sportsImageBanner = R.drawable.ic_bowling_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.cycling,
                subtitleResourceId = R.string.cycling_subtitle,
                imageResourceId = R.drawable.ic_cycling_square,
                sportsImageBanner = R.drawable.ic_cycling_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 6,
                titleResourceId = R.string.golf,
                subtitleResourceId = R.string.golf_subtitle,
                imageResourceId = R.drawable.ic_golf_square,
                sportsImageBanner = R.drawable.ic_golf_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 7,
                titleResourceId = R.string.running,
                subtitleResourceId = R.string.running_subtitle,
                imageResourceId = R.drawable.ic_running_square,
                sportsImageBanner = R.drawable.ic_running_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 8,
                titleResourceId = R.string.soccer,
                subtitleResourceId = R.string.soccer_subtitle,
                imageResourceId = R.drawable.ic_soccer_square,
                sportsImageBanner = R.drawable.ic_soccer_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 9,
                titleResourceId = R.string.swimming,
                subtitleResourceId = R.string.swimming_subtitle,
                imageResourceId = R.drawable.ic_swimming_square,
                sportsImageBanner = R.drawable.ic_swimming_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 10,
                titleResourceId = R.string.table_tennis,
                subtitleResourceId = R.string.table_tennis_subtitle,
                imageResourceId = R.drawable.ic_table_tennis_square,
                sportsImageBanner = R.drawable.ic_table_tennis_banner,
                newsDetails = R.string.sports_news_detail_text
            ),
            Sport(
                id = 11,
                titleResourceId = R.string.tennis,
                subtitleResourceId = R.string.tennis_subtitle,
                imageResourceId = R.drawable.ic_tennis_square,
                sportsImageBanner = R.drawable.ic_tennis_banner,
                newsDetails = R.string.sports_news_detail_text
            )
        )
    }
}
