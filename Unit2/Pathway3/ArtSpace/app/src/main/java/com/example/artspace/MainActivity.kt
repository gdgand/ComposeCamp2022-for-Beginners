package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
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

    var currentNum by remember { mutableStateOf(1) }
    var currentImage by remember { mutableStateOf(R.drawable.image1) }
    var currentTitle by remember { mutableStateOf(R.string.image1_title) }
    var currentArtist by remember { mutableStateOf(R.string.image1_artist) }

    Column {
        PhotoFrame(
            painter = painterResource(id = currentImage)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PhotoInfo(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            title = stringResource(id = currentTitle),
            artist = stringResource(id =currentArtist)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PrevNextButton(
                onClick = {
                    currentNum = when {
                        currentNum == 1 -> 6
                        else -> currentNum - 1
                    }

                    currentImage = when {
                        currentNum == 1 -> R.drawable.image1
                        currentNum == 2 -> R.drawable.image2
                        currentNum == 3 -> R.drawable.image3
                        currentNum == 4 -> R.drawable.image4
                        currentNum == 5 -> R.drawable.image5
                        else -> R.drawable.image6
                    }

                    currentTitle = when {
                        currentNum == 1 -> R.string.image1_title
                        currentNum == 2 -> R.string.image2_title
                        currentNum == 3 -> R.string.image3_title
                        currentNum == 4 -> R.string.image4_title
                        currentNum == 5 -> R.string.image5_title
                        else -> R.string.image6_title
                    }

                    currentArtist = when {
                        currentNum == 1 -> R.string.image1_artist
                        currentNum == 2 -> R.string.image2_artist
                        currentNum == 3 -> R.string.image3_artist
                        currentNum == 4 -> R.string.image4_artist
                        currentNum == 5 -> R.string.image5_artist
                        else -> R.string.image6_artist
                    }
                },
                text = "Previous",
            )
            Spacer(modifier = Modifier.width(24.dp))
            PrevNextButton(
                onClick = {
                    currentNum = when {
                        currentNum == 6 -> 1
                        else -> currentNum + 1
                    }

                    currentImage = when {
                        currentNum == 1 -> R.drawable.image1
                        currentNum == 2 -> R.drawable.image2
                        currentNum == 3 -> R.drawable.image3
                        currentNum == 4 -> R.drawable.image4
                        currentNum == 5 -> R.drawable.image5
                        else -> R.drawable.image6
                    }

                    currentTitle = when {
                        currentNum == 1 -> R.string.image1_title
                        currentNum == 2 -> R.string.image2_title
                        currentNum == 3 -> R.string.image3_title
                        currentNum == 4 -> R.string.image4_title
                        currentNum == 5 -> R.string.image5_title
                        else -> R.string.image6_title
                    }

                    currentArtist = when {
                        currentNum == 1 -> R.string.image1_artist
                        currentNum == 2 -> R.string.image2_artist
                        currentNum == 3 -> R.string.image3_artist
                        currentNum == 4 -> R.string.image4_artist
                        currentNum == 5 -> R.string.image5_artist
                        else -> R.string.image6_artist
                    }
                },
                text = "Next"
            )
        }
    }
}

@Composable
fun PhotoFrame(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .border(width = 2.dp, color = Color.Black)
        )
    }
}

@Composable
fun PhotoInfo(
    modifier: Modifier = Modifier,
    title: String,
    artist: String
) {
    Column(
        modifier = modifier
            .border(width = 2.dp, color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 6.dp, start = 6.dp, end = 6.dp)
        )
        Text(
            text = artist,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun PrevNextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(40.dp)
            .width(120.dp)
    ) {
        Text(
            text = text,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}