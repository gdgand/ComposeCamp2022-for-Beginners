package com.example.lemonade

import android.graphics.drawable.shapes.RoundRectShape
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
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
    var currentCount by remember { mutableStateOf(1) }
    var randomNumber by remember { mutableStateOf(0) }

    val textResource = when (currentCount) {
        1 -> stringResource(id = R.string.tap_lemon_tree)
        2 -> stringResource(id = R.string.tap_lemon)
        3 -> stringResource(id = R.string.tap_lemonade)
        else -> stringResource(id = R.string.tap_glass)
    }
    val paintResource = when (currentCount) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    val description = when (currentCount) {
        1 -> stringResource(id = R.string.lemon_tree_content_description)
        2 -> stringResource(id = R.string.lemon_content_description)
        3 -> stringResource(id = R.string.glass_of_lemonade_content_description)
        else -> stringResource(id = R.string.empty_glass_content_description)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = textResource,
            fontSize = (18.sp)
        )
        Spacer(modifier = Modifier.size(0.dp, 16.dp))
        Image(
            painter = paintResource,
            contentDescription = description,
            alignment = Alignment.Center,
            modifier = Modifier
                .clickable {
                    if (currentCount == 1) {
                        currentCount++;
                        randomNumber = (2..4).random()
                    } else if (currentCount == 2) {
                        if (randomNumber == 0) {
                            currentCount++;
                        } else {
                            randomNumber--;
                        }
                    } else {
                        currentCount++;
                    }
                    currentCount %= 4
                }
                .border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    RoundedCornerShape(4.dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeApp()
}