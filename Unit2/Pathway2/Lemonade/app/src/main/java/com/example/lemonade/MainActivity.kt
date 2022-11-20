package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
@Preview(showBackground = true)
fun LemonadeApp() {
    MakeLemonade(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {
    var level by remember {
        mutableStateOf(1)
    }
    var stringResource = when (level) {
        1 -> R.string.level1
        2 -> R.string.level2
        6 -> R.string.level2
        7 -> R.string.level2
        8 -> R.string.level2
        3 -> R.string.level3
        else -> R.string.level4
    }
    var imageResource = when (level) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        6 -> R.drawable.lemon_squeeze
        7 -> R.drawable.lemon_squeeze
        8 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var contentDesc  = when (level) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        6 -> R.string.lemon
        7 -> R.string.lemon
        8 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = stringResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDesc),
            modifier = Modifier
                .clickable {
                    /**
                     * 1 -> lemon tree
                     * 2,6,7,8 -> squeeze
                     * 3 -> lemonade
                     * 4 -> empty glass
                     */
                    level = when (level) {
                        1 -> 2
                        2 -> (6..8).random()
                        3 -> 4
                        4 -> 1
                        8 -> 7
                        7 -> 6
                        6 -> 3
                        else -> 1
                    }
                }
                .border(1.dp, Color(105, 205, 216))
        )
        Text(text = level.toString())
    }
}