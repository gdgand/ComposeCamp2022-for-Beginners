package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun Main() {

    var nowScreen by remember {
        mutableStateOf(1)
    }

    var randomLemon by remember {
        mutableStateOf((2..4).random())
    }

    var currentLemon by remember {
        mutableStateOf(0)
    }

    val screenInfo = when (nowScreen) {
        1 -> Pair(
            stringResource(id = R.string.desc1), painterResource(id = R.drawable.lemon_tree)
        )
        2 -> Pair(
            stringResource(id = R.string.desc2), painterResource(id = R.drawable.lemon_squeeze)
        )
        3 -> Pair(
            stringResource(id = R.string.desc3), painterResource(id = R.drawable.lemon_drink)
        )
        4 -> Pair(
            stringResource(id = R.string.desc4), painterResource(id = R.drawable.lemon_restart)
        )
        else -> Pair(
            stringResource(id = R.string.desc1), painterResource(id = R.drawable.lemon_tree)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = screenInfo.first, Modifier.padding(bottom = 16.dp))

        Image(painter = screenInfo.second, contentDescription = stringResource(
            id = R.string.lemon_tree
        ), modifier = Modifier.clickable {
            if (nowScreen == 2) {
                currentLemon++
                if (currentLemon < randomLemon) {
                    return@clickable
                } else {
                    currentLemon = 0
                    randomLemon = (2..4).random()
                }
            }

            if (nowScreen == 4) nowScreen = 1
            else nowScreen++
        })

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Main()
        }
    }
}