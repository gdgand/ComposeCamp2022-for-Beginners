package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
fun LemonComposable(
    modifier: Modifier = Modifier,
    imageReSource: Int,
    textResource: Int,
    descriptionResource: Int,
    onImgClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageReSource),
            contentDescription = stringResource(descriptionResource),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(105, 205, 216),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    onImgClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        var step by remember { mutableStateOf(1) }
        var countSqueeze by remember { mutableStateOf(0) }

        when (step) {
            1 -> {
                LemonComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    imageReSource = R.drawable.lemon_tree,
                    textResource = R.string.lemon_tree,
                    descriptionResource = R.string.lemon_tree_description
                ) {
                    step++
                    countSqueeze = (2..4).random()
                }
            }
            2 -> {
                LemonComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    imageReSource = R.drawable.lemon_squeeze,
                    textResource = R.string.lemon_squeeze,
                    descriptionResource = R.string.lemon_squeeze_description
                ) {
                    countSqueeze--
                    if (countSqueeze == 0) {
                        step++
                    }
                }
            }
            3 -> {
                LemonComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    imageReSource = R.drawable.lemon_drink,
                    textResource = R.string.lemon_drink,
                    descriptionResource = R.string.lemon_drink_description
                ) {
                    step++
                }
            }
            4 -> {
                LemonComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    imageReSource = R.drawable.lemon_restart,
                    textResource = R.string.lemon_restart,
                    descriptionResource = R.string.lemon_restart_description
                ) {
                    step = 1
                }
            }
        }
    }
}