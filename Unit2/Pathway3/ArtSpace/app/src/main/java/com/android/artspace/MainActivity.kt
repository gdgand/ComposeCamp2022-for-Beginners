package com.android.artspace

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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.artspace.ui.theme.ArtSpaceTheme

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

@Composable()
fun ArtSpaceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var now by remember {
            mutableStateOf(1)
        }

        var artImageResource: Int = R.drawable.dice_1
        var artContentDescription: String = stringResource(id = R.string.dice1_descrip)
        var artTitle: String = stringResource(id = R.string.art_title_1)
        var artist: String = stringResource(id = R.string.artist_1)

        when(now){
            1 -> {
                artImageResource = R.drawable.dice_1
                artContentDescription = stringResource(id = R.string.dice1_descrip)
                artTitle = stringResource(id = R.string.art_title_1)
                artist = stringResource(id = R.string.artist_1)
            }
            2 -> {
                artImageResource = R.drawable.dice_2
                artContentDescription = stringResource(id = R.string.dice2_descrip)
                artTitle = stringResource(id = R.string.art_title_2)
                artist = stringResource(id = R.string.artist_2)
            }
            3 -> {
                artImageResource = R.drawable.dice_3
                artContentDescription = stringResource(id = R.string.dice3_descrip)
                artTitle = stringResource(id = R.string.art_title_3)
                artist = stringResource(id = R.string.artist_3)
            }
            4 -> {
                artImageResource = R.drawable.dice_4
                artContentDescription = stringResource(id = R.string.dice4_descrip)
                artTitle = stringResource(id = R.string.art_title_4)
                artist = stringResource(id = R.string.artist_4)
            }
            5 -> {
                artImageResource = R.drawable.dice_5
                artContentDescription = stringResource(id = R.string.dice5_descrip)
                artTitle = stringResource(id = R.string.art_title_5)
                artist = stringResource(id = R.string.artist_5)
            }
            6 -> {
                artImageResource = R.drawable.dice_6
                artContentDescription = stringResource(id = R.string.dice6_descrip)
                artTitle = stringResource(id = R.string.art_title_6)
                artist = stringResource(id = R.string.artist_6)
            }
        }

        TopImage(
            artImageResource,
            artContentDescription,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Info(artTitle, artist)
        Spacer(modifier = Modifier.height(16.dp))
        BottomButtons(
            {
                if(now > 1){
                    now--
                }
            },
            {
                if(now < 6){
                    now++
                }
            })
    }
}

@Composable
fun TopImage(
    artImageResource: Int,
    artContentDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
            .border(4.dp, Color.Gray, RectangleShape)
    ) {
        Image(
            painter = painterResource(id = artImageResource),
            contentDescription = artContentDescription,
            modifier = Modifier
                .padding(36.dp)
            ,
        )
    }
}

@Composable
fun Info(
    artTitle: String,
    artist: String,
    modifier: Modifier = Modifier,
) {
    Card(elevation = 4.dp) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Row() {
                Spacer(modifier = modifier.weight(0.05f))
                Text(
                    text = artTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                )
                Spacer(modifier = modifier.weight(0.05f))
            }
            Row() {
                Spacer(modifier = modifier.weight(0.05f))
                Text(
                    text = artist,
                    fontSize = 18.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                )
                Spacer(modifier = modifier.weight(0.05f))
            }
        }
    }

}

@Composable()
fun BottomButtons(
    onClickPreviousButton : () -> Unit,
    onClickNextButton : () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = modifier.weight(0.05f))
        Button(onClick = onClickPreviousButton,
            modifier = modifier
                .weight(0.45f),
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = modifier
            .weight(0.05f))
        Button(onClick = onClickNextButton,
            modifier = modifier
                .weight(0.45f),
        ) {
            Text(text = "Next")
        }
        Spacer(modifier = modifier
            .weight(0.05f))
    }
}