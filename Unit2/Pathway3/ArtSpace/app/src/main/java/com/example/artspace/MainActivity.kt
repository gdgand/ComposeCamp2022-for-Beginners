package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
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
                    ArtSpace()
                }
            }
        }
    }
}


@Composable
fun ArtSpace(){
    var index by remember { mutableStateOf(0) }
    val imageResource = when(index) {
        1 -> R.drawable.green_sea_turtle
        2 -> R.drawable.guzman_barquin
        3 -> R.drawable.hari_nandakumar
        else -> R.drawable.humpback_whale
    }

    val title = when(index) {
        1 -> R.string.green_sea_turtle
        2 -> R.string.guzman_barquin
        3 -> R.string.hari_nandakumar
        else -> R.string.humpback_whale
    }

    val description = when(index) {
        1 -> R.string.green_sea_turtle_description
        2 -> R.string.guzman_barquin_description
        3 -> R.string.hari_nandakumar_description
        else -> R.string.humpback_whale_description
    }


    Gallery(
        image = painterResource(id = imageResource),
        title = stringResource(id = title),
        description = stringResource(id = description),
        onClickPrevious = {
            index -= 1
            if ( index < 0) {
                index = 3
            }
            index
        },
        onClickNext = { index += 1
            if (4 <= index ) {
                index = 0
            }
            index },
    )
}

@Composable
fun Gallery(
    image: Painter,
    title: String,
    description: String,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier,
    ){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.padding(top = 30.dp))
        Image(
            modifier = modifier.height(150.dp),
            painter = image,
            contentDescription = null
        )
        Spacer(modifier = modifier.padding(top = 30.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.padding(top = 5.dp))
        Text(
            text = description,
        )
        Spacer(modifier = modifier.padding(top = 20.dp))
        Row(
        ) {
            Button(
                onClick = { onClickPrevious()  }) {
                Text(text = "이전")
            }

            Spacer(modifier = modifier.padding(20.dp))

            Button(onClick = { onClickNext() }) {
                Text(text = "다음")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}