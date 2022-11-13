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
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currntIndex by remember {
        mutableStateOf(0)
    }

    when (currntIndex) {
        0 -> {
            ArtSpaceScreen(
                imageId = R.drawable.lemon_tree,
                titleId = R.string.lemon_tree,
                descId = R.string.lemon_tree_desc,
                onPrevClick = {
                    currntIndex = 3
                },
                onNextClick = {
                    currntIndex++
                }
            )
        }
        1 -> {
            ArtSpaceScreen(
                imageId = R.drawable.lemon_squeeze,
                titleId = R.string.lemon_squeeze,
                descId = R.string.lemon_squeeze_desc,
                onPrevClick = {
                    currntIndex--
                },
                onNextClick = {
                    currntIndex++
                }
            )
        }
        2 -> {
            ArtSpaceScreen(
                imageId = R.drawable.lemon_drink,
                titleId = R.string.lemon_drink,
                descId = R.string.lemon_drink_desc,
                onPrevClick = {
                    currntIndex--
                },
                onNextClick = {
                    currntIndex++
                }
            )
        }
        3 -> {
            ArtSpaceScreen(
                imageId = R.drawable.lemon_restart,
                titleId = R.string.lemon_restart,
                descId = R.string.lemon_restart_desc,
                onPrevClick = {
                    currntIndex--
                },
                onNextClick = {
                    currntIndex = 0
                }
            )
        }
    }
}

@Composable
fun ArtSpaceScreen(
    imageId: Int,
    titleId: Int,
    descId: Int,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column() {
            Image(
                painter = painterResource(id = imageId), contentDescription = "",
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Black))
                    .padding(10.dp)
            )

        }
        Spacer(modifier = modifier.height(10.dp))
        TitleAndDesc(titleId, descId)
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
fun TitleAndDesc(titleId: Int, descId: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = descId))
        Text(
            text = stringResource(id = titleId),
            fontWeight = FontWeight.Bold, fontSize = 12.sp
        )

    }
}

@Preview
@Composable
fun Preview() {
    ArtSpaceTheme() {
        ArtSpaceApp()
    }
}