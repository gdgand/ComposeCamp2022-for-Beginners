package com.example.myjeju.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myjeju.model.JejuItemContent
import androidx.compose.ui.Alignment

@Composable
fun ItemScreen(
    selectedJejuItem: JejuItemContent,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler() {
        onBackPressed()
    }
    Card(
        elevation = 5.dp,
        modifier = modifier
            .padding(10.dp),
    ) {
        Column (
            modifier = Modifier
                .heightIn(max = 500.dp)
                .padding(4.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = selectedJejuItem.imageResourceId),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = stringResource(id = selectedJejuItem.titleResourceId),
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomStart)
                )
            }
            Text(
                text = stringResource(id = selectedJejuItem.descriptionResourceId),
                modifier = Modifier
                    .padding(8.dp)
            )

        }

    }


}