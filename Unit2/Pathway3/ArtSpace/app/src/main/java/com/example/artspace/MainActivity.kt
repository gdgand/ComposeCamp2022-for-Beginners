package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val modifier = Modifier;

    val arts = arrayOf(
        ArtData("Title1", "Artist1", "2021", R.drawable.img1),
        ArtData("Title2", "Artist2", "2020", R.drawable.img2),
        ArtData("Title3", "Artist3", "2019", R.drawable.img3),
        ArtData("Title4", "Artist4", "2018", R.drawable.img4),
        ArtData("Title5", "Artist5", "2017", R.drawable.img5),
    )

    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(20.dp)
        ) {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = modifier.border(2.dp, Color.Black)
                ) {
                    Image(
                        painter = painterResource(arts[currentIndex].imgResourceId),
                        contentDescription = null,
                        modifier = modifier.padding(20.dp)
                    )
                }
            }

            Card(
                elevation = 5.dp,
                modifier = modifier.padding(top = 20.dp).fillMaxWidth().padding(horizontal = 50.dp)
            ) {
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    Text(
                        text = arts[currentIndex].title
                    )
                    Text(
                        text = "${arts[currentIndex].artist} (${arts[currentIndex].year})"
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val buttonWidth = 130.dp

                Button(
                    onClick = {
                        if (currentIndex <= 0) {
                            currentIndex = arts.size-1
                            return@Button
                        }
                        currentIndex--
                    },
                    modifier = modifier.width(buttonWidth)
                ) {
                    Text(text = "Previous")
                }
                Button(
                    onClick = {
                        if (arts.size-1 <= currentIndex) {
                            currentIndex = 0
                            return@Button
                        }
                        currentIndex++
                    },
                    modifier = modifier.width(buttonWidth)
                ) {
                    Text(text = "Next")
                }
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
            ArtSpaceApp()
        }
    }
}

data class ArtData(val title: String, val artist: String, val year: String, @DrawableRes val imgResourceId: Int)