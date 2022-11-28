package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
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
fun ArtDescText(title:String, artist:String, year:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = Bold
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(artist)
                }
                append(" ")
                append(year)
            },
            fontSize = 16.sp
        )
    }
}

@Composable
fun ArtSpaceApp() {
    var status by remember { mutableStateOf(1) }

    val imageResource = when(status) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResourceForTitle = when(status) {
        1 -> R.string.title1
        2 -> R.string.title2
        3 -> R.string.title3
        else -> R.string.title4
    }

    val stringResourceForArtist = when(status) {
        1 -> R.string.artist1
        2 -> R.string.artist2
        3 -> R.string.artist3
        else -> R.string.artist4
    }

    val stringResourceForYear = when(status) {
        1 -> R.string.year1
        2 -> R.string.year2
        3 -> R.string.year3
        else -> R.string.year4
    }

    Column(modifier = Modifier
        .padding(32.dp)
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Surface(shape = MaterialTheme.shapes.medium, elevation = 5.dp) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = stringResourceForArtist),
                modifier = Modifier
                    .padding(32.dp)
                    .border(
                        BorderStroke(2.dp, Color(55, 105, 116)),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

        Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp) {
            ArtDescText(
                title = stringResource(id = stringResourceForTitle),
                artist = stringResource(id = stringResourceForArtist),
                year = stringResource(id = stringResourceForYear)
            )
        }

        Row() {
            Button(onClick = {if(status == 1)  status = 4 else status--},
                modifier = Modifier.weight(1f)){
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(32.dp))

            Button(onClick = {if(status == 4)  status = 1 else status++}, modifier = Modifier.weight(1f)){
                Text(text = "Next")
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