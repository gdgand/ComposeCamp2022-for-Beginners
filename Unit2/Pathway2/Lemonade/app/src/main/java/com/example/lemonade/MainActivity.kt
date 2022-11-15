package com.example.lemonade

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.widget.ImageButton
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
import androidx.compose.ui.draw.clip
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeClick(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf(1) }
    val image_result = when(screen) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    val image_desc = when(screen) {
        1 -> stringResource(id = R.string.Lemon_tree)
        2 -> stringResource(id = R.string.Lemon)
        3 -> stringResource(id = R.string.Glass_of_lemonade)
        else -> stringResource(id = R.string.Empty_glass)
    }
    val text_result = when(screen) {
        1 -> stringResource(id = R.string.lemon_tree_content_description)
        2 -> stringResource(id = R.string.lemon_content_description)
        3 -> stringResource(id = R.string.glass_of_lemonade_content_description)
        else -> stringResource(id = R.string.empty_glass_content_description)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text_result,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = image_result,
            contentDescription = image_desc,
            modifier = Modifier
                .clickable {
                    if(screen == 4) screen = 1
                    else screen++
                }
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(2.dp, Color(105, 205, 216)))
        )
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeClick(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(
                Alignment.Center
            )
    )
}