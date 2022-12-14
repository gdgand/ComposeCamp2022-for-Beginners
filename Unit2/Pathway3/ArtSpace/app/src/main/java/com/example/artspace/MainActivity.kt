package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {

    val dataset = listOf<ArtWork>(
        ArtWork(R.drawable.image1, R.string.art_title1, R.string.artist1),
        ArtWork(R.drawable.image2, R.string.art_title2, R.string.artist2),
        ArtWork(R.drawable.image3, R.string.art_title3, R.string.artist3),
        ArtWork(R.drawable.image4, R.string.art_title4, R.string.artist4),
        ArtWork(R.drawable.image5, R.string.art_title5, R.string.artist5),
        ArtWork(R.drawable.image6, R.string.art_title6, R.string.artist6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtPageDisplay()
                }
            }
        }
    }

    @Composable
    private fun ArtPageDisplay(
    ) {
        var idx by remember { mutableStateOf(0) }
        val artWork: ArtWork = dataset[idx]
        Log.d("ONCLICK: ", idx.toString())
        val imageId = artWork.imageId
        val titleId = artWork.titleId
        val artistId = artWork.artistId
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Card(
                    shape = RectangleShape,
                    border = BorderStroke(2.dp, Color.Gray),
                    elevation = 8.dp
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier.padding(32.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = titleId),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = stringResource(id = artistId)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (idx == 0) idx = dataset.size - 1
                        else idx--
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Previous")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        if (idx == dataset.size - 1) idx = 0
                        else idx++
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }

    @Composable
    fun ArtPage(
        imageId: Int,
        titleId: Int,
        artistId: Int,
        onPreviousClick: () -> Unit,
        onNextClick: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Card(
                    shape = RectangleShape,
                    border = BorderStroke(2.dp, Color.Gray),
                    elevation = 8.dp
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier.padding(32.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = titleId),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = stringResource(id = artistId)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { onPreviousClick }) {
                    Text(text = "Previous")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = { onNextClick }) {
                    Text(text = "Next")
                }
            }
        }
    }

    @Preview
    @Composable
    fun ArtPagePreview(){
        ArtSpaceTheme() {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                ArtPageDisplay()
            }
        }
    }
}
