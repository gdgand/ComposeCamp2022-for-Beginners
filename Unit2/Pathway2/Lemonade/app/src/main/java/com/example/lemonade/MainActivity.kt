package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var state by remember { mutableStateOf(1) }

        var squeezeCount by remember { mutableStateOf(0) }

        when(state) {
            1 -> LemonWithTextAndImage(
                textLabelResourceId = R.string.message_1,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.image_description_1,
                onImageClick = {
                    state = 2
                    squeezeCount = (2..4).random()
                },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            2 -> LemonWithTextAndImage(
                textLabelResourceId = R.string.message_2,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.image_description_2,
                onImageClick = {
                    squeezeCount--
                    if(squeezeCount == 0) state = 3
                },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            3 -> LemonWithTextAndImage(
                textLabelResourceId = R.string.message_3,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.image_description_3,
                onImageClick = { state = 4 },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            4 -> LemonWithTextAndImage(
                textLabelResourceId = R.string.message_4,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.image_description_4,
                onImageClick = { state = 1 },
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

    }
}

@Composable
fun LemonWithTextAndImage(
        textLabelResourceId: Int,
        drawableResourceId: Int,
        contentDescriptionResourceId: Int,
        onImageClick: () -> Unit,
        modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(textLabelResourceId)
        )
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonApp()
    }
}