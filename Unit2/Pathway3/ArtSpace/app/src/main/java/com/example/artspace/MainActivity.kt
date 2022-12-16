package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

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

@Composable
fun ArtSpaceScreen() {
    var state by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtImageWithDescription(state)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { state-- } ) {
                Text(stringResource(R.string.previous))
            }
            Button(onClick = { state++ } ) {
                Text(stringResource(R.string.next))
            }
            if (state < 0) {
                state = 3
            } else if (state > 3) {
                state = 0
            }
        }
    }
}

@Composable
fun ArtImageWithDescription(
    state: Int
){
    @DrawableRes var artworkId: Int = R.drawable.gentoo_penguin
    @StringRes var screenReaderDescription: Int = R.string.srDesc_0
    @StringRes var artworkTitle: Int = R.string.title_0
    @StringRes var authorInfo: Int = R.string.author_0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val artwork = when (state) {
            1 -> {
                Artwork(R.drawable.gentoo_penguin, R.string.srDesc_0, R.string.title_0, R.string.author_0)
            }
            2 -> {
                Artwork(R.drawable.emperor_penguin, R.string.srDesc_1, R.string.title_1, R.string.author_1)
            }
            3 -> {
                Artwork(R.drawable.chinstrap_penguin, R.string.srDesc_2, R.string.title_2, R.string.author_2)
            }
            else -> {
                Artwork(R.drawable.adelie_penguin, R.string.srDesc_3, R.string.title_3, R.string.author_3)
            }
        }
        artworkId = artwork.imageId
        screenReaderDescription = artwork.screenReaderDescriptionId
        artworkTitle = artwork.artworkTitleId
        authorInfo = artwork.authorInfoId

        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black)
        ) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            if (screenWidth >= 800.dp) {
                Image(
                    painter = painterResource(artworkId),
                    contentDescription = stringResource(screenReaderDescription),
                    modifier = Modifier.padding(30.dp)
                        .fillMaxHeight(0.7F),
                    contentScale = ContentScale.Crop,
                )
            }
            else{
                Image(
                    painter = painterResource(artworkId),
                    contentDescription = stringResource(screenReaderDescription),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7F)
                        .padding(30.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer( modifier = Modifier.height(16.dp) )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Companion.Gray,
                    shape = RoundedCornerShape(10.dp),
                )
        ) {
            Text(
                text = stringResource(artworkTitle),
                fontSize = 36.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(20.dp, 10.dp, 20.dp, 10.dp),
            )
            Text(
                modifier = Modifier
                    .padding(20.dp, 10.dp, 20.dp, 10.dp),
                text = stringResource(authorInfo),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}