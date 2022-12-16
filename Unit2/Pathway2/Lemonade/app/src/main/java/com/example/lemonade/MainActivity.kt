package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LemonadeCreationWithTaps(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeCreationWithTaps(modifier: Modifier = Modifier) {
    var order by remember { mutableStateOf(0) }
    var clickCount by remember { mutableStateOf((2..4).random()) }

    val textList = arrayListOf(
        stringResource(R.string.tab_lemon_desc),
        stringResource(R.string.squeeze_lemon_desc),
        stringResource(R.string.drink_lemonade_desc),
        stringResource(R.string.start_again_desc)
    )
    val imgDescriptionList = arrayListOf(
        stringResource(R.string.img_lemon_tree),
        stringResource(R.string.img_lemon),
        stringResource(R.string.img_lemondate_glass),
        stringResource(R.string.img_empty_glass)
    )
    val imageList = arrayListOf(
        painterResource(R.drawable.lemon_tree),
        painterResource(R.drawable.lemon_squeeze),
        painterResource(R.drawable.lemon_drink),
        painterResource(R.drawable.lemon_restart)
    )

    WelcomeScreen(
        modifier = modifier,
        description = textList[order],
        painterResource = imageList[order],
        screenReaderDescription = imgDescriptionList[order],
        onClickFunction = {
            if (order != 1) {
                order++
                order %= 4
            } else {
                clickCount--
                if (clickCount < 1) {
                    order++
                    clickCount = (2..4).random()
                }
            }
        }
    )

}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, description: String, painterResource: Painter, screenReaderDescription: String, onClickFunction: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = description,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OutlinedButton(
            border = BorderStroke(2.dp, Color(105,205,216)),
            shape = RoundedCornerShape(4.dp),
            onClick = onClickFunction
        ) {
            Image(painter = painterResource, contentDescription = screenReaderDescription)
        }

    }
}