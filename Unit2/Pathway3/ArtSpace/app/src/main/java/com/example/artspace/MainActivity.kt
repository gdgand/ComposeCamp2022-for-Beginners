package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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

    var current by remember { mutableStateOf(1) }
    val imageResource = when(current) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val titleResource = when(current) {
        1 -> R.string.title1
        2 -> R.string.title2
        3 -> R.string.title3
        4 -> R.string.title4
        else -> R.string.title1
    }
    val writerResource = when(current) {
        1 -> R.string.writer1
        2 -> R.string.writer2
        3 -> R.string.writer3
        4 -> R.string.writer4
        else -> R.string.writer1
    }
    val yearResource = when(current) {
        1 -> R.string.year1
        2 -> R.string.year2
        3 -> R.string.year3
        4 -> R.string.year4
        else -> R.string.year1
    }

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Card(
                shape = RectangleShape,
                border = BorderStroke(2.dp, Color.Gray),
                elevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp,
            shape = RectangleShape
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = titleResource),
                    fontSize = 25.sp
                )
                Row {
                    Text(
                        text = stringResource(id = writerResource),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(" + stringResource(id = yearResource) + ")",
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 15.dp, 30.dp, 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (current - 1 < 1) {
                        current = 4
                    } else {
                        current--
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = {
                    if (current + 1 > 4) {
                        current = 1
                    } else {
                        current++
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
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