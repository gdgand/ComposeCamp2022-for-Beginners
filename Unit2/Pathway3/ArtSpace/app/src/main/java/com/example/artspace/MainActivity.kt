package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

private val itemList = listOf(
    ArtItem(R.drawable.android, R.string.str_android, R.string.str_android_by),
    ArtItem(R.drawable.apple_workplace, R.string.str_apple_workplace, R.string.str_apple_workplace_by),
    ArtItem(R.drawable.comet, R.string.str_comet, R.string.str_comet_by),
    ArtItem(R.drawable.sea, R.string.str_sea, R.string.str_sea_by),
    ArtItem(R.drawable.space, R.string.str_space, R.string.str_space_by),
    ArtItem(R.drawable.night_sky, R.string.str_night_sky, R.string.str_night_sky_by),
    ArtItem(R.drawable.mountain, R.string.str_mountain, R.string.str_mountain_by)
)

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
private fun ArtSpaceScreen(
) {
    var step by remember { mutableStateOf(0) }
    val artWork: ArtItem = itemList[step]

    val imageId = artWork.imgId
    val titleId = artWork.titleId
    val discripId = artWork.discripId

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Card(
                shape = RectangleShape,
                border = BorderStroke(5.dp, Color.Gray),
                elevation = 5.dp
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = titleId),
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = titleId),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = stringResource(id = discripId)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (step == 0) {
                        step = itemList.size - 1
                    } else {
                        step -= 1
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.str_previous))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (step == itemList.size - 1) step = 0
                    else step++
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.str_next))
            }
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}


data class ArtItem(
    @DrawableRes val imgId: Int,
    @StringRes val titleId: Int,
    @StringRes val discripId: Int
)