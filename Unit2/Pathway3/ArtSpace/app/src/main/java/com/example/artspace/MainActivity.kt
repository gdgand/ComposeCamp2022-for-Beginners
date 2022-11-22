
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
fun ArtSpaceApp(){
    var currentPage by remember { mutableStateOf(1) }
    Surface(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        when (currentPage) {
            1 -> {
                ArtWork(
                    titleResourceId = R.string.starry_night_title,
                    descriptionResourceId = R.string.starry_night_description,
                    artistResourceId = R.string.starry_night_artist,
                    yearResourceId = R.string.starry_night_year,
                    drawableResourceId = R.drawable.starrynight,
                    onPreviousButtonClick = {
                        currentPage = 5
                    },
                    onNextButtonClick = {
                        currentPage = 2
                    }
                )
            }
            2 -> {
                ArtWork(
                    titleResourceId = R.string.bedroom_title,
                    descriptionResourceId = R.string.bedroom_description,
                    artistResourceId = R.string.bedroom_artist,
                    yearResourceId = R.string.bedroom_year,
                    drawableResourceId = R.drawable.bedroom,
                    onPreviousButtonClick = {
                        currentPage = 1
                    },
                    onNextButtonClick = {
                        currentPage = 3
                    }
                )
            }
            3 -> {
                ArtWork(
                    titleResourceId = R.string.sunflowers_title,
                    descriptionResourceId = R.string.sunflowers_description,
                    artistResourceId = R.string.sunflowers_artist,
                    yearResourceId = R.string.sunflowers_year,
                    drawableResourceId = R.drawable.sunflowers,
                    onPreviousButtonClick = {
                        currentPage = 2
                    },
                    onNextButtonClick = {
                        currentPage = 4
                    }
                )
            }
            4 -> {
                ArtWork(
                    titleResourceId = R.string.launch_title,
                    descriptionResourceId = R.string.launch_description,
                    artistResourceId = R.string.launch_artist,
                    yearResourceId = R.string.launch_year,
                    drawableResourceId = R.drawable.launch,
                    onPreviousButtonClick = {
                        currentPage = 3
                    },
                    onNextButtonClick = {
                        currentPage = 5
                    }
                )
            }
            5 -> {
                ArtWork(
                    titleResourceId = R.string.woman_title,
                    descriptionResourceId = R.string.woman_description,
                    artistResourceId = R.string.woman_artist,
                    yearResourceId = R.string.woman_year,
                    drawableResourceId = R.drawable.woman,
                    onPreviousButtonClick = {
                        currentPage = 4
                    },
                    onNextButtonClick = {
                        currentPage = 1
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

 @Composable
 fun ArtWork(
     titleResourceId: Int,
     descriptionResourceId: Int,
     artistResourceId: Int,
     yearResourceId: Int,
     drawableResourceId: Int,
     onPreviousButtonClick: () -> Unit,
     onNextButtonClick: () -> Unit,
     modifier: Modifier = Modifier
 ) {
     Column(
         modifier = modifier,
         horizontalAlignment = Alignment.CenterHorizontally
     ){
         Image(
             painter = painterResource(id = drawableResourceId),
             contentDescription = stringResource(id = descriptionResourceId)
         )
         Spacer(modifier = modifier.height(32.dp))
         Text(
             text = stringResource(id = titleResourceId),
             fontSize = 20.sp,
             fontWeight = FontWeight.Bold
         )
         Row(){
             Text(
                 text = stringResource(id = artistResourceId)
             )
             Text(
                 text = stringResource(id = yearResourceId)
             )
         }
         Spacer(modifier = modifier.height(32.dp))
         Row(){
             Button(
                 onClick = onPreviousButtonClick,
                 modifier = Modifier.width(100.dp)
             ){
                 Text(stringResource(id = R.string.b_previous))
             }
             Spacer(modifier = modifier.width(16.dp))
             Button(
                 onClick = onNextButtonClick,
                 modifier = modifier.width(100.dp)
             ){
                 Text(stringResource(R.string.b_next))
             }

         }

     }
 }