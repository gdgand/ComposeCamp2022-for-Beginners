package com.example.artspace

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ModifierInfo
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
fun ImageWithFrame(
    painterResource: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Surface(
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
            .shadow(8.dp, RectangleShape)
        ) {
            Image(
                painter = painterResource,
                contentDescription = contentDescription,
                modifier = Modifier
                    .padding(64.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ImageInfoColumn(
    artWorkTitle: String,
    artWorkArtist: String,
    artWorkDate: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 4.dp)
        ) {
            Text(
                text = artWorkTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row() {
                Text(
                    text = artWorkArtist,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = artWorkDate,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var imageNumber by remember { mutableStateOf(1) }

    val painterResource = when (imageNumber) {
        1 -> painterResource(id = R.drawable.image__1_)
        2 -> painterResource(id = R.drawable.image__2_)
        3 -> painterResource(id = R.drawable.image__3_)
        4 -> painterResource(id = R.drawable.image__4_)
        5 -> painterResource(id = R.drawable.image__5_)
        else -> painterResource(id = R.drawable.image__6_)
    }
    val artWorkTitle = when (imageNumber) {
        1 -> stringResource(id = R.string.image1_info_title)
        2 -> stringResource(id = R.string.image2_info_title)
        3 -> stringResource(id = R.string.image3_info_title)
        4 -> stringResource(id = R.string.image4_info_title)
        5 -> stringResource(id = R.string.image5_info_title)
        else -> stringResource(id = R.string.image6_info_title)
    }
    val artWorkArtist = when (imageNumber) {
        1 -> stringResource(id = R.string.image1_info_artist)
        2 -> stringResource(id = R.string.image2_info_artist)
        3 -> stringResource(id = R.string.image3_info_artist)
        4 -> stringResource(id = R.string.image4_info_artist)
        5 -> stringResource(id = R.string.image5_info_artist)
        else -> stringResource(id = R.string.image6_info_artist)
    }
    val artWorkDate = when (imageNumber) {
        1 -> stringResource(id = R.string.image1_info_date)
        2 -> stringResource(id = R.string.image2_info_date)
        3 -> stringResource(id = R.string.image3_info_date)
        4 -> stringResource(id = R.string.image4_info_date)
        5 -> stringResource(id = R.string.image5_info_date)
        else -> stringResource(id = R.string.image6_info_date)

    }
    val contentDescription = when (imageNumber) {
        1 -> stringResource(id = R.string.image1_content_description)
        2 -> stringResource(id = R.string.image2_content_description)
        3 -> stringResource(id = R.string.image3_content_description)
        4 -> stringResource(id = R.string.image4_content_description)
        5 -> stringResource(id = R.string.image5_content_description)
        else -> stringResource(id = R.string.image6_content_description)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageWithFrame(
            painterResource = painterResource,
            contentDescription = contentDescription,
            modifier = Modifier
                .weight(7.0f, true)
                .fillMaxSize()
        )
        ImageInfoColumn(
            artWorkTitle = artWorkTitle,
            artWorkArtist = artWorkArtist,
            artWorkDate = artWorkDate,
            modifier = Modifier
                .weight(2.0f, false)
                .padding(16.dp)
                .wrapContentWidth()
                .shadow(8.dp, RectangleShape)
        )

        Row(
            modifier = Modifier
                .weight(1.0f, false)
                .fillMaxWidth()
                .padding(30.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    imageNumber -= 1
                    if (imageNumber < 0) imageNumber = 5
                },
                modifier = Modifier.width(140.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.button_text_previous),
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    imageNumber += 1
                    imageNumber %= 6
                },
                modifier = Modifier.width(140.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.button_text_previous),
                    fontSize = 16.sp
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