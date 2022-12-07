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
import androidx.compose.material.Surface
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
                // A surface container using the 'background' color from the theme
                Surface {
                    MakeLemonade()
                }
            }
        }
    }
}

@Composable
fun MakeLemonade(){
    var step by remember { mutableStateOf(1) }
    var lemonTapCount by remember { mutableStateOf(0) }

    when(step){
        1 -> {
            showEachStep(
                painter = painterResource(id = R.drawable.lemon_tree),
                title = stringResource(id = R.string.step1_title),
                contentDesc = stringResource(id = R.string.step1_content_description),
                onClickListener = {
                    step = 2
                    lemonTapCount = (2..4).random()
                }
            )
        }
        2 -> {
            showEachStep(
                painter = painterResource(id = R.drawable.lemon_squeeze),
                title = stringResource(id = R.string.step2_title),
                contentDesc = stringResource(id = R.string.step2_content_description),
                onClickListener = {
                    lemonTapCount --
                    if(lemonTapCount == 0) {
                        step = 3
                    }
                }
            )
        }
        3 -> {
            showEachStep(
                painter = painterResource(id = R.drawable.lemon_drink),
                title = stringResource(id = R.string.step3_title),
                contentDesc = stringResource(id = R.string.step3_content_description),
                onClickListener = {
                    step = 4
                }
            )
        }
        else -> {
            showEachStep(
                painter = painterResource(id = R.drawable.lemon_restart),
                title = stringResource(id = R.string.step4_title),
                contentDesc = stringResource(id = R.string.step4_content_description),
                onClickListener = {
                    step = 1
                }
            )
        }
    }
}

@Composable
fun showEachStep(
    painter : Painter,
    title : String,
    contentDesc : String,
    onClickListener : () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = title,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painter,
            contentDescription = contentDesc,
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(1.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(16.dp)
                .clickable(
                    onClick = onClickListener
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        MakeLemonade()
    }
}