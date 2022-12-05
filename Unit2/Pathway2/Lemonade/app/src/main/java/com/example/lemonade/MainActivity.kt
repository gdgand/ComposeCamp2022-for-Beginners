package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember {
        mutableStateOf(1)
    }
    var goalNum by remember {
        mutableStateOf(0)
    }
    var clickNum by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var painterId: Int
        var stringId: Int
        var imgDescriptionId : Int
        val getNextStep = {
            step += 1
            if (step > 4) {
                step = 1
            }
        }
        var todo:()->Unit
        when(step) {
            1 -> {
                painterId = R.drawable.lemon_tree
                stringId = R.string.lemon_tree
                imgDescriptionId = R.string.lemon_tree_img_description
                todo = {
                    clickNum = 0
                    goalNum = (2..4).random()
                    getNextStep()
                }
            }
            2 -> {
                painterId = R.drawable.lemon_squeeze
                stringId = R.string.lemon
                imgDescriptionId = R.string.lemon_img_description
                todo = {
                    clickNum += 1
                    if (clickNum == goalNum) {
                        getNextStep()
                    }
                }
            }

            3 -> {
                painterId = R.drawable.lemon_drink
                stringId = R.string.glass_of_lemonade
                imgDescriptionId = R.string.glass_of_lemonade_img_description
                todo = getNextStep
            }
            
            else -> {
                painterId = R.drawable.lemon_restart
                stringId = R.string.empty_glass
                imgDescriptionId = R.string.empty_glass_img_description
                todo = getNextStep
            }
        }
        Text(
            stringResource(id = stringId),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = todo) {
            Image(painter = painterResource(id = painterId), stringResource(id = imgDescriptionId))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeApp()
}