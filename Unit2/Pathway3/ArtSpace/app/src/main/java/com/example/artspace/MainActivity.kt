package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtworkScreen()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtworkScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_C)
@Composable
fun DefaultPreview2() {
    ArtSpaceTheme {
        ArtworkScreen()
    }
}

@Composable
fun ArtworkScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp, 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var artworkId by remember {
            mutableStateOf(0)
        }

        val artworkResourceId = when (artworkId) {
            0 -> R.drawable.img1
            1 -> R.drawable.img2
            2 -> R.drawable.img3
            3 -> R.drawable.img4
            else -> R.drawable.img5
        }

        val artworkTitle = when (artworkId) {
            0 -> "Title 1"
            1 -> "Title 2 Title 2"
            2 -> "Title 3 Title 3 Title 3"
            3 -> "Title 4 Title 4 Title 4 Title 4"
            else -> "Title 5 Title 5 Title 5 Title 5 Title 5"
        }

        val artistName = when (artworkId) {
            0 -> "artist 1"
            1 -> "artist 2"
            2 -> "artist 3"
            3 -> "artist 4"
            else -> "artist 5"
        }

        val year = when (artworkId) {
            0 -> 1980
            1 -> 2021
            2 -> 2010
            3 -> 2000
            else -> 2022
        }

        Artwork(artworkResourceId, Modifier.align(CenterHorizontally))
        Spacer(modifier = Modifier.height(32.dp))
        Description(
            modifier = Modifier.align(CenterHorizontally),
            title = artworkTitle, artist = artistName, year = year
        )
        Spacer(modifier = Modifier.height(32.dp))
        NavigationControls(
            onClickPrevious = {
                if (artworkId > 0) {
                    artworkId--
                }
            },
            onClickNext = {
                if (artworkId < 4) {
                    artworkId++
                }
            },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun Artwork(artworkId: Int, modifier: Modifier = Modifier) {
    val painter = painterResource(id = artworkId)
    val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .shadow(16.dp)
            .border(2.dp, Color.Gray)
            .background(Color.White)
            .padding(16.dp)
            .fillMaxHeight(.6f)
            .aspectRatio(imageRatio)
    )
}

@Composable
fun Description(modifier: Modifier = Modifier, title: String, artist: String, year: Int) {
    Surface(
        modifier = modifier.shadow(4.dp, RoundedCornerShape(2.dp)),
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = title, fontSize = 32.sp, color = Color.DarkGray)
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(artist)
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(" ($year)")
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationControls(
    modifier: Modifier = Modifier,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.Bottom
    ) {
        Button(onClick = onClickPrevious,
            modifier = modifier
                .weight(1.0f)
                .wrapContentSize()
                .defaultMinSize(150.dp)
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onClickNext,
            modifier = modifier
                .weight(1f)
                .wrapContentSize()
                .defaultMinSize(150.dp)
        ) {
            Text(text = "Next")
        }
    }
}
