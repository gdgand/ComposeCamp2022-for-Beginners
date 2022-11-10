package com.wookhyun.lemonade

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wookhyun.lemonade.ui.theme.LemonadeTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonAdeApp() {
    LemonAdeApp()
}

@Composable
fun LemonScreen() {
    var step by remember {
        mutableStateOf(0)
    }

    var desc = when (step) {
        0 -> stringResource(id = R.string.first_step)
        1 -> stringResource(id = R.string.second_step)
        2 -> stringResource(id = R.string.third_step)
        else -> stringResource(id = R.string.fourth_step)
    }

    var imagePainter = when (step) {
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = desc)
        Image(
            painter = imagePainter, contentDescription = null,
            modifier = Modifier.clickable {
                if (step == 1) {
                    if ((1..6).random() == 6) {
                        step = ++step % 4
                    }
                } else {
                    step = ++step % 4
                }
                Log.d(TAG, "LemonScreen: $step")
            },
        )
    }
}