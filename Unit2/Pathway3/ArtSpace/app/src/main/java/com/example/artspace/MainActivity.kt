package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val modifier = Modifier;

    val arts = arrayOf(
        ArtData("Title1", "Artist1", "2021", R.drawable.img1),
        ArtData("", "", "", R.drawable.img2),
        ArtData("", "", "", R.drawable.img3),
        ArtData("", "", "", R.drawable.img4),
        ArtData("", "", "", R.drawable.img5),
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Box {
            Box(
                modifier = modifier.border(2.dp, Color.Black)
            ) {
                Image(
                    painter = painterResource(arts[currentIndex].imgResourceId),
                    contentDescription = null,
                    modifier = modifier.padding(20.dp)
                )
            }
        }

        Card(
            elevation = 5.dp,
            modifier = modifier.padding(top = 20.dp).fillMaxWidth().padding(horizontal = 50.dp)
        ) {
            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(
                    text = arts[currentIndex].title
                )
                Text(
                    text = "${arts[currentIndex].artist} (${arts[currentIndex].year})"
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val buttonWidth = 130.dp

            Button(
                onClick = { /*TODO*/ },
                modifier = modifier.width(buttonWidth)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier.width(buttonWidth)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ArtSpaceApp()
        }
    }
}

data class ArtData(val title: String, val artist: String, val year: String, @DrawableRes val imgResourceId: Int)