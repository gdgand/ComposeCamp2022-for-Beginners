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

@Preview(showBackground = true)
@Composable
fun LemonadeTextWithImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var sqeeze_count by remember { mutableStateOf(2) }
    var textresource = when (result) {
        1 -> R.string.lemon_tree_text
        2 -> R.string.lemon_squeeze_text
        3 -> R.string.lemon_drink_text
        else -> R.string.lemon_restart_text
    }
    var imageresource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var imagedescriptionresource = when (result) {
        1 -> R.string.lemon_tree_image
        2 -> R.string.lemon_squeeze_image
        3 -> R.string.lemon_drink_image
        else -> R.string.lemon_restart_image
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textresource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = imageresource),
            contentDescription = stringResource(id = imagedescriptionresource),
            Modifier
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    if (result == 1) {
                        sqeeze_count = (2..4).random()
                        result++
                    } else if (result == 2) {
                        if (sqeeze_count != 0) {
                            sqeeze_count--
                        }
                        if (sqeeze_count == 0) {
                            result++
                        }
                    } else if (result == 3)
                        result++
                    else if (result == 4) {
                        result = 1
                    }
                })
    }
}


@Preview(showSystemUi = true)
@Composable
fun LemonadeApp() {
    LemonadeTextWithImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}