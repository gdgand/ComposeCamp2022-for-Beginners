package com.example.myjeju.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myjeju.model.JejuItemContent

@Composable
fun ItemScreen(
    selectedJejuItem: JejuItemContent,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler() {
        onBackPressed()
    }
    Column (
        modifier = modifier.padding(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = selectedJejuItem.imageResourceId),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = selectedJejuItem.titleResourceId),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Text(
            text = stringResource(id = selectedJejuItem.descriptionResourceId)
        )

    }

}