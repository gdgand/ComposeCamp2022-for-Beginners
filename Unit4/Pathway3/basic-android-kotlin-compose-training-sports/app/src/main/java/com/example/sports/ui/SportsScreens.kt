/*
 * Copyright (c) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sports.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sports.R
import com.example.sports.data.LocalSportsDataProvider
import com.example.sports.model.Sport
import com.example.sports.ui.theme.SportsTheme

/**
 * Main composable that serves as container
 * which displays content according to [uiState]
 */
@Composable
fun SportsApp(
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val viewModel: SportsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val contentType = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> ContentType.List
        WindowWidthSizeClass.Medium -> ContentType.List
        WindowWidthSizeClass.Expanded -> ContentType.ListAndDetail
        else -> ContentType.List
    }

    Scaffold(
        topBar = {
            SportsAppBar(
                contentType = contentType,
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() }
            )
        }
    ) { innerPadding ->
        if (contentType == ContentType.ListAndDetail) {
            val activity = LocalContext.current as Activity
            SportsListAndDetails(
                sports = uiState.sportsList,
                selectedSport = uiState.currentSport,
                onClick = {
                    viewModel.updateCurrentSport(it)
                    viewModel.navigateToDetailPage()
                },
                onBackPressed = {
                    activity.finish()
                }
            )
        } else {
            if (uiState.isShowingListPage) {
                SportsList(
                    sports = uiState.sportsList,
                    onClick = {
                        viewModel.updateCurrentSport(it)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = modifier.padding((innerPadding))
                )
            } else {
                SportsDetail(
                    selectedSport = uiState.currentSport,
                    modifier = modifier.padding((innerPadding)),
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun SportsAppBar(
    contentType: ContentType,
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                if (contentType == ContentType.ListAndDetail) {
                    stringResource(R.string.list_fragment_label)
                } else {
                    if (!isShowingListPage) {
                        stringResource(R.string.news_fragment_label)
                    } else {
                        stringResource(R.string.list_fragment_label)
                    }
                }
            )
        },
        navigationIcon = if (contentType == ContentType.ListAndDetail) {
            null
        } else {
            if (!isShowingListPage) {
                {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            } else {
                null
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SportsListItem(
    sport: Sport,
    onItemClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 2.dp,
        modifier = modifier,
        onClick = { onItemClick(sport) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        ) {
            SportsListImageItem(sport)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(sport.titleResourceId),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(8.dp)

                )
                Text(
                    text = stringResource(R.string.news_title),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(sport.subtitleResourceId),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun SportsListImageItem(sport: Sport, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(170.dp)
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(sport.imageResourceId),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun SportsList(
    sports: List<Sport>,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(sports, key = { sport -> sport.id }) { sport ->
            SportsListItem(
                sport = sport,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier.padding(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(selectedSport.sportsImageBanner),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(selectedSport.titleResourceId),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Text(
            text = stringResource(R.string.news_title),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(selectedSport.newsDetails),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun SportsListAndDetails(
    modifier: Modifier = Modifier,
    sports: List<Sport>,
    selectedSport: Sport,
    onClick: (Sport) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    Row(modifier = modifier.fillMaxSize()) {
        SportsList(modifier = Modifier.weight(1f), sports = sports, onClick = onClick)
        SportsDetail(
            modifier = Modifier.weight(1f),
            selectedSport = selectedSport,
            onBackPressed = onBackPressed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SportsListItemPreview() {
    SportsTheme {
        SportsListItem(
            sport = LocalSportsDataProvider.defaultSport,
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SportsListPreview() {
    SportsTheme() {
        Surface {
            SportsList(
                sports = LocalSportsDataProvider.getSportsData(),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun SportsListAndDetailsPreview() {
    SportsTheme() {
        Surface {
            SportsListAndDetails(
                sports = LocalSportsDataProvider.getSportsData(),
                selectedSport = LocalSportsDataProvider.getSportsData()[0]
            )
        }
    }
}
