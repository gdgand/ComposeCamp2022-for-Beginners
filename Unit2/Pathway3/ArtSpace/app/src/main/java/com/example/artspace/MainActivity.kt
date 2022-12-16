package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.data.ArtData
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

    val artDatas = listOf(
        ArtData(
            artNumber = 1,
            artDrawableResourceId = R.drawable.lemon_tree,
            artworkTitle = "Lemon Tree",
            artworkArtist = "compose",
            year = 2022
        ),
        ArtData(
            artNumber = 2,
            artDrawableResourceId = R.drawable.lemon_squeeze,
            artworkTitle = "Lemon Squeeze",
            artworkArtist = "compose",
            year = 2022
        ),
        ArtData(
            artNumber = 3,
            artDrawableResourceId = R.drawable.lemon_drink,
            artworkTitle = "Lemon Drink",
            artworkArtist = "compose",
            year = 2022
        ),
        ArtData(
            artNumber = 4,
            artDrawableResourceId = R.drawable.lemon_restart,
            artworkTitle = "Lemon Restart",
            artworkArtist = "compose",
            year = 2022
        )
    )

    var artDataIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(artDrawableResourceId = artDatas[artDataIndex].artDrawableResourceId)
        Spacer(modifier = Modifier.height(8.dp))
        ArtworkDescriptor(
            artworkTitle = artDatas[artDataIndex].artworkTitle,
            artworkArtist = artDatas[artDataIndex].artworkArtist,
            year = artDatas[artDataIndex].year
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { if (artDataIndex == 0) artDataIndex = 3 else artDataIndex -= 1 },
                modifier = Modifier.weight(1F)
            ) {
                Text(text = stringResource(id = R.string.previousButton))
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = { if (artDataIndex == 3) artDataIndex = 0 else artDataIndex  += 1 },
                modifier = Modifier.weight(1F)
            ) {
                Text(text = stringResource(id = R.string.nextButton))
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun ArtworkWall(
    artDrawableResourceId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(
                BorderStroke(2.dp, Color(54, 69, 79)),
            ),
        elevation = 4.dp) {
        Image(
            painter = painterResource(id = artDrawableResourceId),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp))
    }
}

@Composable
fun ArtworkDescriptor(
    artworkTitle: String,
    artworkArtist: String,
    year: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ,
        elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = artworkTitle,
                fontSize = 36.sp)
            Text(
                text = "$artworkArtist($year)",
                fontSize = 18.sp)
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