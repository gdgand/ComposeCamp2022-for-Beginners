package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
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

// @Preview(showBackground = true)
@Composable()
fun ArtSpaceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var nowArtState by remember {
            mutableStateOf(1)
        }

        var artImageResource: Int = R.drawable.lemon_tree
        var artContentDescription: String = stringResource(id = R.string.lemon_tree_descrip)
        var artTitle: String = stringResource(id = R.string.art_title_1)
        var artist: String = stringResource(id = R.string.artist_1)

        when(nowArtState){
            1 -> {
                artImageResource = R.drawable.lemon_tree
                artContentDescription = stringResource(id = R.string.lemon_tree_descrip)
                artTitle = stringResource(id = R.string.art_title_1)
                artist = stringResource(id = R.string.artist_1)
            }
            2 -> {
                artImageResource = R.drawable.lemon_squeeze
                artContentDescription = stringResource(id = R.string.lemon_squeeze_descrip)
                artTitle = stringResource(id = R.string.art_title_2)
                artist = stringResource(id = R.string.artist_2)
            }
            3 -> {
                artImageResource = R.drawable.lemon_drink
                artContentDescription = stringResource(id = R.string.lemon_descrip)
                artTitle = stringResource(id = R.string.art_title_3)
                artist = stringResource(id = R.string.artist_3)
            }
            4 -> {
                artImageResource = R.drawable.lemon_restart
                artContentDescription = stringResource(id = R.string.lemon_restart_descrip)
                artTitle = stringResource(id = R.string.art_title_4)
                artist = stringResource(id = R.string.artist_4)
            }
        }

        TopImageGallery(
            artImageResource,
            artContentDescription,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.height(16.dp))
        CenterInfo(artTitle, artist)
        Spacer(modifier = Modifier.height(16.dp))
        BottomButtons(
            {
                if(nowArtState > 1){
                    nowArtState--
                }
            },
            {
                if(nowArtState < 4){
                    nowArtState++
                }
            })
    }
}

// @Preview(showBackground = true)
@Composable
fun TopImageGallery(
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

// @Preview(showBackground = true)
@Composable
fun CenterInfo(
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

// @Preview(showBackground = true)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}