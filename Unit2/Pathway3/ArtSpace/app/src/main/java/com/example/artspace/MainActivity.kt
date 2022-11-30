package com.example.artspace

import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var artworkStates by remember { mutableStateOf(0) }
    val imageInfo = when (artworkStates) {
        0 -> listOf(R.drawable.art1, R.string.art1_desc, R.string.art1_title, R.string.art1_artist)
        1 -> listOf(R.drawable.art2, R.string.art2_desc, R.string.art2_title, R.string.art2_artist)
        2 -> listOf(R.drawable.art3, R.string.art3_desc, R.string.art3_title, R.string.art3_artist)
        else -> return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Gallery(image = imageInfo[0], contentDescription = imageInfo[1])
        Spacer(modifier = Modifier.padding(16.dp))
        GalleryInfo(title = imageInfo[2], artist = imageInfo[3])
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    --artworkStates
                    if (artworkStates < 0) artworkStates = 2
                }) {
                Text("Previous")
            }
            Button(
                onClick = {
                    ++artworkStates
                    if (artworkStates > 2) artworkStates = 0
                }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Gallery(image: Int, contentDescription: Int) {
    Surface(
        elevation = 20.dp,
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun GalleryInfo(title: Int, artist: Int, modifier: Modifier = Modifier) {
    Card(elevation = 4.dp) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Row() {
                Text(
                    text = stringResource(id = title),
                    fontSize = 28.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                )
            }
            Row {
                Text(
                    text = stringResource(id = artist),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = " (2022)",
                    fontSize = 16.sp,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}