package com.konai.lemonade

import android.os.Bundle
import android.widget.Space
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
import com.konai.lemonade.ui.theme.LemonadeTheme

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

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var randomCount by remember { mutableStateOf(1) }
    Surface(modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background) {
        when(currentStep) {
            1 -> {
                LemonBorder(R.string.tap_the_lemon, R.drawable.lemon_tree, R.string.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        randomCount = (2..4).random()
                    })
            }
            2 -> {
                LemonBorder(R.string.keep_tapping, R.drawable.lemon_squeeze, R.string.lemon,
                    onImageClick = {
                        randomCount--
                        if (randomCount == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                LemonBorder(R.string.tap_the_lemonade, R.drawable.lemon_drink, R.string.glass_of_lemonade,
                    onImageClick = {
                        currentStep =4
                    })
            }
            else -> { // 4
                LemonBorder(R.string.tap_the_empty, R.drawable.lemon_restart, R.string.empty_glass,
                onImageClick = {
                    currentStep =1
                })
            }
        }
    }
}

@Composable
fun LemonBorder(textLabelResourceId: Int, drawableResourceId: Int,
                contentDescriptionResourceId: Int, onImageClick: () -> Unit,modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = textLabelResourceId), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = drawableResourceId), contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}