package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                    InitArtSpace()
                }
            }
        }
    }
}

@Composable
fun InitArtSpace() {

    var page by remember {
        mutableStateOf(1)
    }

    when(page){
        1 -> {
            ShowArtSpace(
                R.drawable.picture_1,
                R.string.picture_1_title,
                R.string.picture_1_artist,
                R.string.picture_1_content_description,
                previousOnClickListener = {
                    page = 3
                },
                nextOnClickListener = {
                    page = 2
                }
            )
        }
        2 -> {
            ShowArtSpace(
                R.drawable.picture_2,
                R.string.picture_2_title,
                R.string.picture_2_artist,
                R.string.picture_2_content_description,
                previousOnClickListener = {
                    page = 1
                },
                nextOnClickListener = {
                    page = 3
                }
            )
        }
        else -> {
            ShowArtSpace(
                R.drawable.picture_3,
                R.string.picture_3_title,
                R.string.picture_3_artist,
                R.string.picture_3_content_description,
                previousOnClickListener = {
                    page = 2
                },
                nextOnClickListener = {
                    page = 1
                }
            )
        }
    }
}

@Composable
fun ShowArtSpace(
    imageId : Int,
    titleId : Int,
    artistId : Int,
    contentDescId : Int,
    previousOnClickListener: () -> Unit,
    nextOnClickListener: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Surface(
            elevation = 5.dp,
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = contentDescId),
                modifier = Modifier.padding(20.dp),
                contentScale = ContentScale.Inside
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            elevation = 5.dp,
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier.wrapContentSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = stringResource(id = titleId),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(id = artistId),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Previous",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .border(
                        BorderStroke(1.dp, color = Color.Black),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clickable(
                        onClick = previousOnClickListener
                    )
            )

            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Next",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(5.dp))
                    .border(
                        BorderStroke(1.dp, color = Color.Black),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clickable(
                        onClick = nextOnClickListener
                    )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        InitArtSpace()
    }
}