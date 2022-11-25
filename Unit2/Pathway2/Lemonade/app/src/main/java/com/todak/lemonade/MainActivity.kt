package com.todak.lemonade

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todak.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonApp() {
    LemonadeTheme {
        LemonadeWithButtonAndImage(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}


@Composable
fun LemonadeWithButtonAndImage(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var cnt by remember { mutableStateOf(0) }
        var squeezeCount by remember { mutableStateOf(2) }
        var pageNumber by remember { mutableStateOf(0) }
        val imageResource = when (pageNumber) {
            0 -> R.drawable.lemon_tree
            1 -> R.drawable.lemon_squeeze
            2 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        val stringResource = when (pageNumber) {
            0 -> R.string.lemon_tree
            1 -> R.string.lemon
            2 -> R.string.glass_of_lemonade
            else -> R.string.empty_glass
        }
        Text(text = stringResource(id = stringResource), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            when (pageNumber) {
                0 ->  {
                    squeezeCount = (2..4).random()
                    pageNumber++
                }
                1 -> {
                    if (cnt == squeezeCount) {
                        cnt = 0
                        pageNumber++
                    } else {
                        cnt++
                    }
                }
                2 -> pageNumber++
                else -> pageNumber = 0
            }
        }) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = pageNumber.toString()
            )
        }
    }
}