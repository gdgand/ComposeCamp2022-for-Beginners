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
            LemonadeApp()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Lemonade(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(1) }
    var touchCnt by remember { mutableStateOf(4) }
    when (state) {
        1 -> {
            LemonadeWithTextAndImage(
                modifier = modifier,
                text = R.string.lemon_1,
                desText = R.string.lemon_des_1,
                paint = painterResource(id = R.drawable.lemon_tree)
            ) {
                state = 2
            }
        }
        2 -> {
            touchCnt = (2..4).random()
            LemonadeWithTextAndImage(
                modifier = modifier,
                text = R.string.lemon_2,
                desText = R.string.lemon_des_2,
                paint = painterResource(id = R.drawable.lemon_squeeze)
            ) {
                touchCnt--
                if (touchCnt <= 0) {
                    state = 3
                }
            }
        }
        3 -> {
            LemonadeWithTextAndImage(
                modifier = modifier,
                text = R.string.lemon_3,
                desText = R.string.lemon_des_3,
                paint = painterResource(id = R.drawable.lemon_drink)
            ) {
                state = 4
            }
        }
        else -> {
            LemonadeWithTextAndImage(
                modifier = modifier,
                text = R.string.lemon_4,
                desText = R.string.lemon_des_4,
                paint = painterResource(id = R.drawable.lemon_restart)
            ) {
                state = 1
            }
        }
    }

}

@Composable
fun LemonadeWithTextAndImage(
    modifier: Modifier = Modifier,
    text: Int,
    desText: Int,
    paint: Painter,
    onClickLemon: () -> (Unit)
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = paint,
            contentDescription = stringResource(id = desText),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onClickLemon
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}