package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background) {
                ArtSpaceTheme {
                    ArtApp(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}


@Composable
fun ArtApp(modifier: Modifier) {
    // A surface container using the 'background' color from the theme
    var result by remember { mutableStateOf(0) }
    var squeeze by remember { mutableStateOf(0) }
    if (result % 4 == 1) squeeze = (2..4).random()
    val imageResource = when (result % 4) {
        0 -> {
            R.drawable.lemon_tree
        }
        1 -> {
            R.drawable.lemon_squeeze
        }
        2 -> R.drawable.lemon_drink
        3 -> R.drawable.lemon_restart
        else -> {
            R.drawable.lemon_tree
        }
    }
    val stringResource = when (result % 4) {
        0 -> {
            R.string.tap
        }
        1 -> R.string.keep
        2 -> R.string.tap2
        3 -> R.string.tap3
        else -> {
            R.string.tap
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.width(10.dp))

        Image(modifier = Modifier
            .border(
                border = BorderStroke(2.dp, Color(105, 205, 216)),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center),
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(Modifier.border(border = BorderStroke(1.dp, Color(105, 205, 216)),
            shape = RoundedCornerShape(2.dp))) {
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(text = stringResource(id = stringResource), fontSize = 32.sp)
                Text(text = stringResource(id = stringResource), fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Button(onClick = {
                if (result == 0) {

                } else {
                    result--
                }
            }) {
                Text(text = "Previous")

            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { result++ }) {
                Text(text = "Next")

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtApp(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}