package com.compose.camp.hun.artspace

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.camp.hun.artspace.ui.theme.ArtSpaceTheme

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center), color = MaterialTheme.colors.background
        ) {
            ArtSpace()
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            elevation = 8.dp,
            border = BorderStroke(width = 1.5.dp, color = Color.DarkGray),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
        ArtDescription("Artwork Title", "artwork desc", "(1995)", modifier)
        BottomButton(
            modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Bottom)
        )
    }
}

@Composable
fun ArtDescription(title: String, desc: String, year: String, modifier: Modifier = Modifier) {
    Surface(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = title, fontSize = 32.sp)
            Row {
                Text(text = "$desc ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = year)
            }
        }
    }
}

@Composable
fun BottomButton(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(text = "previous")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(text = "next")
        }
    }
}