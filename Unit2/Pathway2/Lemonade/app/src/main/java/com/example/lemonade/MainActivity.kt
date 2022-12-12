package com.example.lemonade

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                /*
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
                 */
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    Lemonade(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}
/* del
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
 */

@Preview(showBackground = true)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember { mutableStateOf(0) }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//
        Text(text = stringResource(id = getStatusDesc(currentStep)), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = getStatusImage(currentStep)),
            contentDescription = stringResource(id = getStatusDesc(currentStep)),
            modifier = Modifier
                .border(BorderStroke(2.dp, Color(105, 205, 216)))
                .clickable {
                    if (currentStep == 2) {
                        if (squeezeCount == 0) {
                            squeezeCount = (2..4).random()
                        } else if (squeezeCount == 1) {
                            squeezeCount = 0
                            currentStep = (currentStep + 1) % 4
                        } else {
                            squeezeCount -= 1
                        }
                    } else {
                        currentStep = (currentStep + 1) % 4
                    }
                }
        )

    }
}
/* del
fun DefaultPreview() {

}
 */


fun getStatusDesc(status: Int):Int {
    var result = when(status) {
        1 -> R.string.text_1
        2 -> R.string.text_2
        3 -> R.string.text_3
        else -> R.string.text_4
    }
    return result
}

fun getStatusImage(status: Int):Int {
    var result = when(status) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    return result
}