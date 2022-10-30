/*
 * Copyright (c) 2022 The Android Open Source Project
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

package com.example.sports.ui

import androidx.lifecycle.ViewModel
import com.example.sports.data.LocalSportsDataProvider
import com.example.sports.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * View Model for Sports app
 */
class SportsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SportsUiState(
            sportsList = LocalSportsDataProvider.getSportsData(),
            currentSport = LocalSportsDataProvider.getSportsData().getOrElse(0) {
                LocalSportsDataProvider.defaultSport
            }
        )
    )
    val uiState: StateFlow<SportsUiState> = _uiState

    fun updateCurrentSport(selectedSport: Sport) {
        _uiState.update {
            it.copy(currentSport = selectedSport)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class SportsUiState(
    val sportsList: List<Sport> = emptyList(),
    val currentSport: Sport = LocalSportsDataProvider.defaultSport,
    val isShowingListPage: Boolean = true
)
