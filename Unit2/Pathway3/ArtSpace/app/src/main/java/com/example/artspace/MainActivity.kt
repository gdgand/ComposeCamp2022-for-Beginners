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
fun ArtSpaceScreen() {

    var index by remember {
        mutableStateOf(0)
    }

    val image = when (index) {
        0 -> painterResource(id = R.drawable.image_1)
        1 -> painterResource(id = R.drawable.image_2)
        2 -> painterResource(id = R.drawable.image_3)
        else -> painterResource(id = R.drawable.image_1)
    }

    val contentDesc = when (index) {
        0 -> "buldak"
        1 -> "abon"
        2 -> "redmage"
        else -> "buldak"
    }

    val title = when (index) {
        0 -> "Buldak"
        1 -> "Abon"
        2 -> "Red Mage"
        else -> "Buldak"
    }

    val artist = when (index) {
        0 -> "NEXON"
        1 -> "NEXON"
        2 -> "SQUARE ENIX"
        else -> "NEXON"
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        ArtArea(image, contentDesc, title, artist)
        Spacer(modifier = Modifier.height(10.dp))
        ControlButtonArea({
            index = when (index) {
                0 -> 2
                else -> index - 1
        } }) {
            index = when (index) {
                2 -> 0
                else -> index + 1
            }
        }
        Text(text = index.toString())
    }
}

@Composable
fun ArtArea(
    image : Painter,
    contentDescription : String,
    title: String,
    artist: String
) {
    Column() {
        Image(painter = image, contentDescription = contentDescription)
        Spacer(modifier = Modifier.height(10.dp))
        ArtTitleArea(title, artist)
    }
}

@Composable
fun ArtTitleArea(title : String, artist : String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = artist)
    }
}

@Composable
fun ControlButtonArea(
    preClick: () -> Unit,
    nextClick: () -> Unit
) {
    Row() {
        Button(onClick = preClick) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = nextClick) {
            Text(text = "Next")
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