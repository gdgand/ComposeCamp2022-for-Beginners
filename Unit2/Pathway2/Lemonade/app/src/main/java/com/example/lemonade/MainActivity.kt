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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentImage by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentImage) {
            1 -> {
                LemonTextAndImage(
                    textId = R.string.tap_lemon_tree,
                    imageId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.image_lemon_tree,
                    imageClickFunction = {
                        currentImage = 2

                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    textId = R.string.tap_lemon_squeeze,
                    imageId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.image_lemon,
                    imageClickFunction = {

                        squeezeCount--

                        if (squeezeCount == 0) {
                            currentImage = 3
                        }
                    })
            }
            3 -> {
                LemonTextAndImage(
                    textId = R.string.tap_lemonade,
                    imageId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.image_lemonade,
                    imageClickFunction = {
                        currentImage = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    textId = R.string.tap_empty_glass,
                    imageId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.image_empty_glass,
                    imageClickFunction = {
                        currentImage = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textId: Int,
    imageId: Int,
    contentDescriptionId: Int,
    imageClickFunction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(contentDescriptionId),
            modifier = modifier
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    RoundedCornerShape(4.dp)
                )
                .clickable(
                    onClick = imageClickFunction
                )
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonadeApp()
    }
}