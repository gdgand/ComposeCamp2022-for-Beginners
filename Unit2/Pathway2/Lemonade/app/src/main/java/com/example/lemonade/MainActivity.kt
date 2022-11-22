package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
            LemonadeTheme {
                LemonadeApp();
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    var stageNumber by remember {
        mutableStateOf(1)
    }

    val maxSqueezeCount = (2..4).random()
    var squeezeCount = 0

    val modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)

    println("Initialize count")
    println("maxSqueezeCount:$maxSqueezeCount")
    println("squeezeCount:$squeezeCount")

    when (stageNumber) {
        1 -> Stage(
            stageDescription = stringResource(id = R.string.stage1_description),
            painterResource = painterResource(id = R.drawable.lemon_tree),
            imageDescription = stringResource(id = R.string.lemon_tree_content_description),
            modifier,
            Modifier.clickable { stageNumber = 2 }
        )
        2 -> Stage(
            stageDescription = stringResource(id = R.string.stage2_description),
            painterResource = painterResource(id = R.drawable.lemon_squeeze),
            imageDescription = stringResource(id = R.string.lemon_content_description),
            modifier,
            Modifier.clickable {
                squeezeCount++
                if (squeezeCount >= maxSqueezeCount) {
                    stageNumber = 3

                }
                println("######### clickable ###########")
                println("maxSqueezeCount:$maxSqueezeCount")
                println("squeezeCount:$squeezeCount")
                println("######### clickable ###########")
            }
        )
        3 -> Stage(
            stageDescription = stringResource(id = R.string.stage3_description),
            painterResource = painterResource(id = R.drawable.lemon_drink),
            imageDescription = stringResource(id = R.string.glass_of_lemonade_content_description),
            modifier,
            Modifier.clickable { stageNumber = 4 }
        )
        else -> Stage(
            stageDescription = stringResource(id = R.string.stage4_description),
            painterResource = painterResource(id = R.drawable.lemon_restart),
            imageDescription = stringResource(id = R.string.empty_glass_content_description),
            modifier
                .background(Color.White),
            Modifier.clickable { stageNumber = 1 }
        )
    }
}

@Composable
fun Stage(
    stageDescription: String,
    painterResource: Painter,
    imageDescription: String,
    modifier: Modifier = Modifier,
    clickable: Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stageDescription, fontSize = 18.sp, modifier = Modifier.wrapContentWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource,
            contentDescription = imageDescription,
            modifier = clickable
                .border(1.dp, Color(105, 205, 216), RoundedCornerShape(4.dp))

        )
    }
}
