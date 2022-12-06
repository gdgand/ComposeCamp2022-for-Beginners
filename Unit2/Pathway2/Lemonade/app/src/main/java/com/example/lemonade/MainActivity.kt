package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    DiceWithButtonAndImage()
}

@Composable
fun DiceWithButtonAndImage() {
    var currentStep by remember { mutableStateOf(1) }
    val imageResource = when(currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val imageTitle = when(currentStep) {
        1 -> R.string.lemon_1
        2 -> R.string.lemon_2
        3 -> R.string.lemon_3
        4 -> R.string.lemon_4
        else -> R.string.lemon_1
    }
    val imageDescription = when(currentStep) {
        1 -> R.string.image_desc_1
        2 -> R.string.image_desc_2
        3 -> R.string.image_desc_3
        4 -> R.string.image_desc_4
        else -> R.string.image_desc_1
    }

    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)
    ) {
        Text(
            stringResource(imageTitle),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(height = 16.dp)
        )
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(imageDescription),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 1.dp,
                    color = Color(red = 105, green = 205, blue = 216)
                )
                .clickable {
                    currentStep = (currentStep % 4) + 1
                    Log.e("test", "currentStep :: ${currentStep}")
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}