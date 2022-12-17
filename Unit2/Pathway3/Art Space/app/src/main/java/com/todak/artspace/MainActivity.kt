package com.todak.artspace

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todak.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var currentPage by remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        when (currentPage) {
            1 -> {
                ArtGalleryImageAndTextAndButton(
                    R.drawable.starry_night,
                    currentPage.toString(),
                    R.string.starry_night,
                    onClickPrevious = {
                        currentPage = 3
                    },
                    onClickNext = {
                        currentPage = 2
                    },
                )
            }
            2 -> {
                ArtGalleryImageAndTextAndButton(
                    R.drawable.basket_apple,
                    currentPage.toString(),
                    R.string.basket_apple,
                    onClickPrevious = {
                        currentPage = 1
                    },
                    onClickNext = {
                        currentPage = 3
                    },
                )
            }
            3 -> {
                ArtGalleryImageAndTextAndButton(
                    R.drawable.ship_riding,
                    currentPage.toString(),
                    R.string.ship_riding,
                    onClickPrevious = {
                        currentPage = 2
                    },
                    onClickNext = {
                        currentPage = 1
                    },
                )
            }


        }

    }

}

@Composable
fun ArtGalleryImageAndTextAndButton(
    drawableResId: Int,
    contentDescriptionResId: String,
    stringResId: Int,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(drawableResId), contentDescriptionResId)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(stringResId),
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,

            )
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onClickPrevious) {
                Text(text = stringResource(R.string.button_previous), color = Color.White)
            }
            Spacer(Modifier.size(100.dp))
            Button(onClick = onClickNext) {
                Text(text = stringResource(R.string.button_next), color = Color.White)

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