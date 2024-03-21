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

@Preview
@Composable
fun LemonadeApp() {
    var cnt by remember { mutableStateOf(1) }
    when (cnt) {
        1 -> {
            LemonadeImageText(
                image = R.drawable.lemon_tree,
                text = R.string.txt_1,
                modifier = Modifier.clickable {
                    cnt = 2
                })
        }
        2,3,4 -> {
            val random = (2..4).random()
            LemonadeImageText(
                image = R.drawable.lemon_squeeze,
                text = R.string.txt_2,
                modifier = Modifier.clickable {
                    cnt++
                    if(cnt == random){
                        cnt = 5
                    }
                })
        }
        5 -> {
            LemonadeImageText(
                image = R.drawable.lemon_drink,
                text = R.string.txt_3,
                modifier = Modifier.clickable {
                    cnt = 6
                })
        }
        6 -> {
            LemonadeImageText(
                image = R.drawable.lemon_restart,
                text = R.string.txt_4,
                modifier = Modifier.clickable {
                    cnt = 1
                })
        }

    }
}

fun random(): Int {
    return (1..4).random()
}

@Composable
fun LemonadeImageText(image: Int, text: Int, modifier: Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val imageResource = painterResource(id = image)
        val stringResource = stringResource(id = text)
        Text(text = stringResource, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = imageResource, contentDescription = null,
            modifier = modifier.border(
                width = 1.dp,
                color = Color(105, 205, 216),
                shape = RoundedCornerShape(4.dp)
            )
        )
    }
}