package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
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
                    ArtworkPage(artWorkSamples)
                }
            }
        }
    }
}

@Composable
fun ArtworkPage(artWorks: List<ArtWork>) {
    var currentPage by remember { mutableStateOf(0) }
    Log.e("TAG", "artworks size : ${artWorks.size}")
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 28.dp, end = 28.dp)
    ) {
        Spacer(modifier = Modifier.size(20.dp).weight(1f))

        ArtworkGallery(
            artWork = artWorks[currentPage],
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .align(Alignment.CenterHorizontally)
                .border(border = BorderStroke(2.dp, Color.Gray), shape = RectangleShape)
                .padding(all = 20.dp)
        )

        Spacer(modifier = Modifier.size(20.dp).weight(1f))

        ArtWorkDetail(
            artWork = artWorks[currentPage],
            modifier = Modifier.wrapContentSize()
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 14.dp),
                onClick = {
                    if (currentPage > 0) {
                        currentPage -= 1
                    }
                }
            ) { Text("Previous") }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp),
                onClick = {
                    if (currentPage < artWorks.size - 1) {
                        currentPage += 1
                    }
                }
            ) { Text("Next") }
        }
    }
}

@Composable
fun ArtworkGallery(artWork: ArtWork, modifier: Modifier) {
    Surface(
        elevation = 10.dp, modifier = modifier
    ) {
        Image(painter = painterResource(id = artWork.drawableResId),
            contentDescription = null,
        )
    }
}

@Composable
fun ArtWorkDetail(artWork: ArtWork, modifier: Modifier) {
    Surface(elevation = 10.dp, modifier = modifier) {
        Column(modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 16.dp)
        ) {
            Text(text = artWork.title, fontSize = 22.sp)
            Row {
                Text(text = artWork.author, fontWeight = FontWeight.Bold)
                Text(text = " (${artWork.year})")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ArtworkPage(artWorkSamples)
        }
    }
}

data class ArtWork(
    val title: String,
    val author: String,
    val year: Int,
    val drawableResId: Int,
)

val artWorkSamples = listOf (
    ArtWork("Be like a lotus", "Jetpack Compose", 2022, R.drawable.be_like_a_lotus),
    ArtWork("Blue rose", "Jetpack Compose", 2022, R.drawable.blue_rose),
    ArtWork("Cold winter in california", "Jetpack Compose", 2022, R.drawable.cold_winter_in_california),
    ArtWork("Cookie", "Jetpack Compose", 2022, R.drawable.cookie),
    ArtWork("Murray", "Jetpack Compose", 2022, R.drawable.murray),
    ArtWork("Paris in the rain", "Jetpack Compose", 2022, R.drawable.paris_in_the_rain),
    ArtWork("Sailing under bridge", "Jetpack Compose", 2022, R.drawable.sailing_under_bridge),
    ArtWork("Saturday morning", "Jetpack Compose", 2022, R.drawable.saturday_morning)
)
