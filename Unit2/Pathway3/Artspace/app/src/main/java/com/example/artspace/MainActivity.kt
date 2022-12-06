package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.draw.shadow
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
    var result by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                when (result) {
                    1 -> R.drawable.one
                    2 -> R.drawable.two
                    3 -> R.drawable.three
                    4 -> R.drawable.lemon_tree
                    else -> R.drawable.lemon_squeeze
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .weight(4f)
                .border(
                    BorderStroke(5.dp, color = Color.DarkGray),
                )
                .shadow(elevation = 10.dp)

        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .shadow(elevation = 5.dp)
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(
                    when (result) {
                        1 -> R.string.one_title
                        2 -> R.string.two_title
                        3 -> R.string.three_title
                        4 -> R.string.four_title
                        else -> R.string.five_title
                    }
                ),
                fontSize = 32.sp,
            )
            Text(
                text = stringResource(
                    when (result) {
                        1 -> R.string.one_artist
                        2 -> R.string.two_artist
                        3 -> R.string.three_artist
                        4 -> R.string.four_artist
                        else -> R.string.five_artist
                    }
                ),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = {
                    if (result == 1)
                        result = 5
                    else result--
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.previous_button))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    if (result == 5)
                        result = 1
                    else result++
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.next_button))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}