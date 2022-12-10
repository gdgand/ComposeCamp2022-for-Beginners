package com.example.artspace

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val maxLength = 4
    var index by remember { mutableStateOf(0) }
    val imageId = when (index) {
        1 -> R.drawable.artwork_1
        2 -> R.drawable.artwork_2
        3 -> R.drawable.artwork_3
        else -> R.drawable.artwork_4
    }
    val imageTitle = when (index) {
        1 -> R.string.artwork_title_1
        2 -> R.string.artwork_title_2
        3 -> R.string.artwork_title_3
        else -> R.string.artwork_title_4
    }
    val imageArtist = when (index) {
        1 -> R.string.artwork_artist_1
        2 -> R.string.artwork_artist_2
        3 -> R.string.artwork_artist_3
        else -> R.string.artwork_artist_4
    }
    val imageYear = when (index) {
        1 -> R.string.artwork_year_1
        2 -> R.string.artwork_year_2
        3 -> R.string.artwork_year_3
        else -> R.string.artwork_year_4
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtworkImage(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = imageTitle),
            modifier = Modifier.weight(1f, true).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        ArtworkDescription(
            name = stringResource(id = imageTitle),
            artist = stringResource(id = imageArtist),
            year = stringResource(id = imageYear)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (index <= 0) {
                        index = (maxLength - 1)
                    }

                    index -= 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.prev_button))
            }
            Button(
                onClick = {
                    if (index >= (maxLength - 1)) {
                        index = 0
                    }

                    index += 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.next_button))
            }
        }
    }
}

@Composable
fun ArtworkImage(painter: Painter, contentDescription: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.border(width = 2.dp, color = Color.Gray, shape = RectangleShape),
        elevation = 4.dp
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ArtworkDescription(name: String, artist: String, year: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(name, fontSize = 24.sp, fontWeight = FontWeight.Light, maxLines = 2)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(artist, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "($year)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}