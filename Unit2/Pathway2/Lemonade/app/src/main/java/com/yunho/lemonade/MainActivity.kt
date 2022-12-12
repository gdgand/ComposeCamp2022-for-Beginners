package com.yunho.lemonade

import android.os.Bundle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunho.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

enum class STEP {
    FIRST, SECOND, THIRD, LAST
}

@Composable
fun LemonApp() {
    var step by remember { mutableStateOf(STEP.FIRST) }
    var pressedCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(step) {
            STEP.FIRST -> MainContents(
                textId = R.string.text_select,
                drawableId = R.drawable.lemon_tree,
                descriptionId = R.string.lemon_tree,
                onClick = {
                    step = STEP.SECOND
                    pressedCount = (2..4).random()
                }
            )
            STEP.SECOND -> MainContents(
                textId = R.string.text_squeeze,
                drawableId = R.drawable.lemon_squeeze,
                descriptionId = R.string.lemon,
                onClick = {
                    pressedCount--
                    if (pressedCount == 0) {
                        step = STEP.THIRD
                    }
                }
            )
            STEP.THIRD -> MainContents(
                textId = R.string.text_drink,
                drawableId = R.drawable.lemon_drink,
                descriptionId = R.string.glass_of_lemonade,
                onClick = { step = STEP.LAST }
            )
            STEP.LAST -> MainContents(
                textId = R.string.text_empty,
                drawableId = R.drawable.lemon_restart,
                descriptionId = R.string.empty_glass,
                onClick = { step = STEP.FIRST }
            )
        }
    }
}

@Composable
fun MainContents(
    textId: Int,
    drawableId: Int,
    descriptionId: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(drawableId),
            contentDescription = stringResource(descriptionId),
            modifier = Modifier.wrapContentSize().padding(16.dp)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = onClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    LemonadeTheme {
        LemonApp()
    }
}