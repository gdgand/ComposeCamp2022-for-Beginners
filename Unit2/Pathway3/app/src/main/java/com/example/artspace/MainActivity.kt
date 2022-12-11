package com.example.artspace

import android.os.Bundle
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.R
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
                    artSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun artSpaceScreen() {
    var currentSeq by remember {
        mutableStateOf(1)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentSeq) {
            1 -> {
                artWallImageAndText(
                    drawableResourceId = R.drawable.android_logo,
                    titleResourceId = R.string.art_title_1,
                    artistResourceId = R.string.artist_1,
                    yearResourceId = R.string.year_1
                )
            }
            2 -> {
                artWallImageAndText(
                    drawableResourceId = R.drawable.androidparty,
                    titleResourceId = R.string.art_title_2,
                    artistResourceId = R.string.artist_2,
                    yearResourceId = R.string.year_2
                )
            }
            3 -> {
                artWallImageAndText(
                    drawableResourceId = R.drawable.bg_compose_background,
                    titleResourceId = R.string.art_title_3,
                    artistResourceId = R.string.artist_3,
                    yearResourceId = R.string.year_3
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(onClick = {
            if (currentSeq >= 2) currentSeq--
            else currentSeq = 3
        }) {
            Text(stringResource(R.string.prev))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            if (currentSeq < 3) currentSeq++
            else currentSeq = 1
        }) {
            Text(stringResource(R.string.next))
        }
    }

}


@Composable
fun artWallImageAndText(
    drawableResourceId: Int,
    titleResourceId: Int,
    artistResourceId: Int,
    yearResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
            .padding(24.dp)
    ) {
        Card(
            modifier = Modifier.wrapContentSize()
                .shadow(elevation = 9.dp)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        BorderStroke(4.dp, Color.Gray),
                    )
                    .padding(16.dp)
                    .shadow(elevation = 1.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .wrapContentSize()
                .shadow(3.dp)
                .padding(25.dp)
        ) {
            Text(
                text = stringResource(titleResourceId),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(artistResourceId))
                    }
                    append(" " + stringResource(yearResourceId))
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        artSpaceScreen()
    }
}