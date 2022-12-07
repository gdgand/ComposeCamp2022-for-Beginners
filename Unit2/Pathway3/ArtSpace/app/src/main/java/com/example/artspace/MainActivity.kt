package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var currentIdx by remember {
            mutableStateOf(0)
        }
        when (currentIdx) {
            0 -> {
                ArtSpaceFrame(
                    imageId = R.drawable.cat,
                    titleId = R.string.cat,
                    photographerId = R.string.jeong,
                    onPrevClick = {
                        currentIdx = 3
                    },
                    onNextClick = {
                        currentIdx++
                    }
                )
            }
            1 -> {
                ArtSpaceFrame(
                    imageId = R.drawable.dog,
                    titleId = R.string.dog,
                    photographerId = R.string.lee,
                    onPrevClick = {
                        currentIdx--
                    },
                    onNextClick = {
                        currentIdx++
                    }
                )
            }
            2 -> {
                ArtSpaceFrame(
                    imageId = R.drawable.fox,
                    titleId = R.string.fox,
                    photographerId = R.string.kate,
                    onPrevClick = {
                        currentIdx--
                    },
                    onNextClick = {
                        currentIdx++
                    }
                )
            }
            3 -> {
                ArtSpaceFrame(
                    imageId = R.drawable.tiger,
                    titleId = R.string.tiger,
                    photographerId = R.string.tom,
                    onPrevClick = {
                        currentIdx--
                    },
                    onNextClick = {
                        currentIdx = 0
                    }
                )
            }
        }
    }
}

@Composable
fun ArtSpaceFrame(
    imageId: Int,
    titleId: Int,
    photographerId: Int,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = imageId), contentDescription = "",
            modifier = Modifier
                .size(250.dp)
                .border(1.dp, Color.Black)
                .padding(10.dp)
        )
        Spacer(modifier = modifier.height(10.dp))
        TitleAndPhotographer(titleId, photographerId)
        Spacer(modifier = modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onPrevClick) {
                Text(
                    text = "Previous",
                    fontSize = 8.sp
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = onNextClick) {
                Text(
                    "Next",
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
fun TitleAndPhotographer(titleId: Int, photographerId: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(photographerId))
        Text(
            text = stringResource(titleId),
            fontWeight = FontWeight.Bold, fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}