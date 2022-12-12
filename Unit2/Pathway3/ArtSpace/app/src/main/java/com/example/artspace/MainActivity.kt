package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
                    ViewArt()
                }
            }
        }
    }
}

@Composable
fun ViewArt() {
    var artState by remember { mutableStateOf(1) }

    var artId = when (artState) {
        1 -> R.drawable.bear
        2 -> R.drawable.bird
        3 -> R.drawable.car
        4 -> R.drawable.cat
        5 -> R.drawable.building
        else -> R.drawable.sea
    }

    var artTitle = when (artState) {
        1 -> R.string.bear_image
        2 -> R.string.bird_image
        3 -> R.string.car_image
        4 -> R.string.cat_image
        5 -> R.string.building_image
        else -> R.string.sea_image
    }

    var artDescription = when (artState) {
        1 -> R.string.bear_image_description
        2 -> R.string.bird_image_description
        3 -> R.string.car_image_description
        4 -> R.string.cat_image_description
        5 -> R.string.building_image_description
        else -> R.string.sea_image_description
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        ArtImageField(artId = artId, modifier = Modifier.weight(3f))
        ArtImageInfoField(
            artTitle = artTitle,
            artDescription = artDescription,
            modifier = Modifier.weight(1f)
        )
        ButtonForChangeArt(
            {
                if (artState == 6) artState = 1
                else artState++
            },
            {
                if (artState == 1) artState = 6
                else artState--
            }
        )
    }
}

@Composable
fun ArtImageField(
    artId: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        Surface(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
                .border(width = 2.dp, color = Color(135, 135, 135)),
            elevation = 4.dp
        ) {
            Image(
                painter = painterResource(artId),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(32.dp)
            )
        }
    }

}

@Composable
fun ArtImageInfoField(artTitle: Int, artDescription: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Surface(
            modifier = Modifier
                .border(BorderStroke(4.dp, Color.Transparent)),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(artTitle),
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(artDescription),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ButtonForChangeArt(onClickNext: () -> Unit, onClickPrevious: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onClickPrevious
        ) {
            Text(text = stringResource(id = R.string.previous_button))
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onClickNext
        ) {
            Text(text = stringResource(id = R.string.next_button))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ViewArt()
    }
}