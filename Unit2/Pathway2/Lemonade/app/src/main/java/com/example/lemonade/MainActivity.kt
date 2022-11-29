package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Lemonade(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}


@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    val step by remember {
        mutableStateOf(1)
    }
    val resultImg = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val resultString = when(step) {
        1 -> R.string.lemon_text_01
        2 -> R.string.lemon_text_02
        3 -> R.string.lemon_text_03
        4 -> R.string.lemon_text_04
        else -> R.string.lemon_text_01
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(resultString), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            modifier = Modifier.border(2.dp, color = Color(105,205,216), shape = RoundedCornerShape(4)),
            painter = painterResource(resultImg),
            contentDescription = step.toString(),
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Lemonade()
    }
}