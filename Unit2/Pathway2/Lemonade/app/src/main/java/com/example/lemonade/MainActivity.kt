package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var squeezeTime by remember { mutableStateOf(0) }
    val text: String
    val image: Painter
    val imageDesc: String

    when (step) {
        1 -> {
            text = stringResource(id = R.string.lemon_tree_text)
            image = painterResource(id = R.drawable.lemon_tree)
            imageDesc = stringResource(id = R.string.lemon_tree)
        }
        2 -> {
            text = stringResource(id = R.string.lemon_text)
            image = painterResource(id = R.drawable.lemon_squeeze)
            imageDesc = stringResource(id = R.string.lemon)
            squeezeTime = (2..4).random()
        }
        3 -> {
            text = stringResource(id = R.string.glass_of_lemonade_text)
            image = painterResource(id = R.drawable.lemon_drink)
            imageDesc = stringResource(id = R.string.glass_of_lemonade)
        }
        else -> {
            text = stringResource(id = R.string.empty_glass_text)
            image = painterResource(id = R.drawable.lemon_restart)
            imageDesc = stringResource(id = R.string.empty_glass)
        }
    }

    LemonadeImageAndText(
        text = text,
        image = image,
        imageDesc = imageDesc,
        modifier = modifier,
        onClick = {
            when (step) {
                2 -> {
                    if (--squeezeTime == 0) step = 3
                }
                4 -> step = 1
                else -> step = step.plus(1)
            }
        }
    )
}

@Composable
fun LemonadeImageAndText(
    text: String,
    image: Painter,
    imageDesc: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = text, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = image,
            contentDescription = imageDesc,
            modifier = Modifier
                .clickable(onClick = onClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    RoundedCornerShape(4.dp)
                )
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