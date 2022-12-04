package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ArtSpaceTheme {
        ArtworkApp()
      }
    }
  }
}

@Composable
fun ArtworkApp() {
  var current by remember { mutableStateOf(1) }

  when (current) {
    1 -> Artwork(
      titleId = R.string.cat,
      artistId = R.string.artist,
      yearId = R.string.cat_year,
      painterId = R.drawable.cat,
      onPreviousClicked = { current = 3 },
      onNextClicked = { current = 2 }
    )
    2 -> Artwork(
      titleId = R.string.germany_building,
      artistId = R.string.artist,
      yearId = R.string.germany_building_year,
      painterId = R.drawable.germany_building,
      onPreviousClicked = { current = 1 },
      onNextClicked = { current = 3 }
    )
    else -> Artwork(
      titleId = R.string.sunset,
      artistId = R.string.artist,
      yearId = R.string.sunset_year,
      painterId = R.drawable.sunset,
      onPreviousClicked = { current = 2 },
      onNextClicked = { current = 1 }
    )
  }
}

@Composable
fun Artwork(
  titleId: Int,
  artistId: Int,
  yearId: Int,
  painterId: Int,
  onPreviousClicked: () -> Unit,
  onNextClicked: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ArtImage(painterId)
    Spacer(modifier = Modifier.padding(top = 20.dp))
    ArtText(titleId, artistId, yearId)
    Spacer(modifier = Modifier.padding(top = 20.dp))
    PreviewNextButton(onPreviousClicked, onNextClicked)
  }
}

@Composable
private fun ArtImage(painterId: Int) {
  Surface(
    modifier = Modifier.height(440.dp),
    elevation = 5.dp,
    border = BorderStroke(3.dp, Color.Gray)
  ) {
    Image(
      modifier = Modifier.padding(40.dp),
      painter = painterResource(painterId),
      contentDescription = null,
      contentScale = ContentScale.Crop
    )
  }
}

@Composable
private fun ArtText(titleId: Int, artistId: Int, yearId: Int) {
  Surface(modifier = Modifier.fillMaxWidth(), elevation = 5.dp) {
    Text(
      buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 25.sp, fontWeight = FontWeight.Light)) {
          append(stringResource(titleId) + "\n")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
          append(stringResource(artistId) + " ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight)) {
          append("(" + stringResource(yearId) + ")")
        }
      },
      modifier = Modifier.padding(20.dp)
    )
  }
}

@Composable
private fun PreviewNextButton(onPreviousClicked: () -> Unit, onNextClicked: () -> Unit) {
  Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
    Button(modifier = Modifier
      .fillMaxWidth()
      .weight(0.5f)
      .padding(end = 15.dp), onClick = { onPreviousClicked() }) {
      Text("Previous")
    }
    Button(modifier = Modifier
      .fillMaxWidth()
      .weight(0.5f)
      .padding(start = 15.dp), onClick = { onNextClicked() }) {
      Text("Next")
    }
  }
}