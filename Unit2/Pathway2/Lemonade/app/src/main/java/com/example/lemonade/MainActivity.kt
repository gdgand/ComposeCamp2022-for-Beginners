package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
                MakeLemonadeApp()
            }
        }
    }
}

@Composable
fun MakeLemonadeApp(){
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(currentStep){
            1 -> {
                LemonadeTextAndImage(
                    R.string.select_lemon,
                    R.drawable.lemon_tree,
                    R.string.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonadeTextAndImage(
                    R.string.squeeze_lemon,
                    R.drawable.lemon_squeeze,
                    R.string.lemon,
                    onImageClick = {
                        squeezeCount--
                        if(squeezeCount == 0) {
                            currentStep = 3
                        }
                    }

                )
            }
            3 -> {
                LemonadeTextAndImage(
                    R.string.drink_lemonade,
                    R.drawable.lemon_drink,
                    R.string.glass_of_lemonade,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonadeTextAndImage(
                    R.string.restart_lemonade,
                    R.drawable.lemon_restart,
                    R.string.empty_glass,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }

    }
}

@Composable
fun LemonadeTextAndImage(
    textResource: Int,
    imageResource: Int,
    contentDescriptionResource: Int,
    onImageClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(contentDescriptionResource),
            modifier = Modifier
                .border(3f.dp, colorResource(R.color.border))
                .clickable {
                    onImageClick()
                }
            )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        MakeLemonadeApp()
    }
}