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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme

                    LemonApp()

            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        when (currentStep) {
            1 -> {
                lemonFunction(R.string.lemon_tree, R.drawable.lemon_tree, onStartClicked = {

                    currentStep = 2
                    squeezeCount = (2..4).random()
                })


            }
            2 -> {

                lemonFunction(R.string.lemon_squeeze, R.drawable.lemon_squeeze, onStartClicked = {


                    squeezeCount--

                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                })

            }

            3 -> {

                lemonFunction(R.string.lemon_drink, R.drawable.lemon_drink, onStartClicked = {

                    currentStep = 4
                })


            }
            4 -> {
                lemonFunction(R.string.lemon_restart, R.drawable.lemon_restart, onStartClicked = {
                    // Back to starting step
                    currentStep = 1
                })


            }

        }
    }
}

@Composable
fun lemonFunction(name: Int, image: Int, onStartClicked: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = stringResource(name),   fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(name),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onStartClicked
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