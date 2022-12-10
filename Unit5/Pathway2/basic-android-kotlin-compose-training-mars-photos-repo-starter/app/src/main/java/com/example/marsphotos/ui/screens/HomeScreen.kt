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
package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.theme.MarsPhotosTheme

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier)
        // is MarsUiState.Success -> ResultScreen(marsUiState.photos, modifier)
        // is MarsUiState.Success -> MarsPhotoCard(photo = marsUiState.photos, modifier = modifier)
        is MarsUiState.Success -> PhotosGridScreen(marsUiState.photos, modifier)
        is MarsUiState.Error -> ErrorScreen(modifier)
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

/**
 * The home screen displaying error message
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}

/**
 * The home screen displaying number of retrieved photos.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(photos)
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 8.dp
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.mars_photo),
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
        )
    }
}

@Composable
fun PhotosGridScreen(
    photos: List<MarsPhoto>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(items = photos, key = { photo -> photo.id }) {
            photo
            -> MarsPhotoCard(photo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MarsPhotosTheme {
        ResultScreen(stringResource(R.string.result))
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        PhotosGridScreen(mockData)
    }
}
