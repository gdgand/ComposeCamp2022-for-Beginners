package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                    ArtSpaceMain()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceMain() {
    var current by remember {
        mutableStateOf(1)
    }

    when (current) {
        1 -> ArtSpace(
            imgn = R.drawable.img1,
            imgnTitle = R.string.img1title,
            imgnInfo = R.string.img1info,
            imgnYear = R.string.img1year,
            buttonPrevious = { current = 4 },
            buttonNext = { current = 2 }
        )

        2 -> ArtSpace(
            imgn = R.drawable.img2,
            imgnTitle = R.string.img2title,
            imgnInfo = R.string.img2info,
            imgnYear = R.string.img2year,
            buttonPrevious = { current = 1 },
            buttonNext = { current = 3 }
        )

        3 -> ArtSpace(
            imgn = R.drawable.img3,
            imgnTitle = R.string.img3title,
            imgnInfo = R.string.img3info,
            imgnYear = R.string.img3year,
            buttonPrevious = { current = 2 },
            buttonNext = { current = 4 }
        )

        4 -> ArtSpace(
            imgn = R.drawable.img4,
            imgnTitle = R.string.img4title,
            imgnInfo = R.string.img4info,
            imgnYear = R.string.img4year,
            buttonPrevious = { current = 3 },
            buttonNext = { current = 1 }
        )
    }
}

@Composable
fun ArtSpace(
    imgn: Int,
    imgnTitle: Int,
    imgnInfo: Int,
    imgnYear: Int,
    buttonPrevious: () -> Unit,
    buttonNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NImage(imgn)
        Spacer(modifier = Modifier.padding(17.dp))
        NTitleInfo(imgnTitle, imgnInfo, imgnYear)
        Spacer(modifier = Modifier.padding(17.dp))
        Buttons(buttonPrevious, buttonNext)
    }
}

@Composable
private fun Buttons(buttonPrevious: () -> Unit, buttonNext: () -> Unit) {
    Row(modifier = Modifier.padding(10.dp)) {
        Button(
            onClick = { buttonPrevious() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(text = "Previous")
        }

        Button(
            onClick = { buttonNext() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
private fun NTitleInfo(imgnTitle: Int, imgnInfo: Int, imgnYear: Int) {
    Surface(modifier = Modifier.fillMaxWidth(), elevation = 7.dp) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Light)) {
                    append(stringResource(imgnTitle) + "\n")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(imgnInfo) + " ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight)) {
                    append(stringResource(imgnYear))
                }
            },
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
private fun NImage(imgn: Int) {
    Surface(
        modifier = Modifier.height(450.dp),
        elevation = 5.dp,
        border = BorderStroke(3.dp, Color.Gray)
    ) {
        Image(
            painter = painterResource(id = imgn),
            contentDescription = null,
            modifier = Modifier.padding(40.dp),
            contentScale = ContentScale.Crop
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ArtSpaceTheme {
//
//    }
//}