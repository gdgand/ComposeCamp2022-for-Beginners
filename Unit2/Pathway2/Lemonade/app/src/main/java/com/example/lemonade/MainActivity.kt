package com.example.lemonade

import android.os.Bundle
import android.util.Log
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
import com.kimjiun.lemonade.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeThemeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeThemeApp() {
    LemonadeTextAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeTextAndImage(modifier: Modifier = Modifier) {
    var status by remember { mutableStateOf(1) }
    var count by remember { mutableStateOf(0) }

    val imageResource = when(status) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResourceForText = when(status) {
        1 -> R.string.lemon_description1
        2 -> R.string.lemon_description2
        3 -> R.string.lemon_description3
        else -> R.string.lemon_description4
    }

    val stringResourceForContent = when(status) {
        1 -> R.string.lemon_content_description1
        2 -> R.string.lemon_content_description2
        3 -> R.string.lemon_content_description3
        else -> R.string.lemon_content_description4
    }

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = stringResourceForText)
            , fontSize = 16.sp)
        if(status == 2){
            Text(text = "$count Times", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = stringResourceForContent),
            Modifier.clickable {
                when(status){
                    1, 3 -> {
                        status++
                        count = (2..4).random()
                    }
                    2 -> {
                        if(--count == 0){
                            status++
                        }
                    }
                    4 -> {
                        status = 1
                    }
                }
                Log.d("HHHH", "status : $status")
            }
                .border(BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp))
        )
    }
}