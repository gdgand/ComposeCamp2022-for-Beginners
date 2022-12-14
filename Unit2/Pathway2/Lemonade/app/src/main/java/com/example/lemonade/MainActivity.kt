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
import com.example.lemonade.ui.theme.LemonadeTheme

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
    var tap_random by remember { mutableStateOf(1) }
    var cnt_tap by remember { mutableStateOf(0) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(stringResource(id = R.string.tap_lemon_tree),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.lemon_tree),
                        contentDescription = stringResource(id = R.string.lemon_tree),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 2
                                tap_random = (2..4).random()

                            }
                            .border(
                                BorderStroke(2.dp, Color(105, 205, 216)),
                                shape = RoundedCornerShape(4.dp)
                            ))
                }

            }
            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(stringResource(id = R.string.keep_tapping),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.lemon_squeeze),
                        contentDescription = stringResource(id = R.string.lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                cnt_tap++
                                if (cnt_tap == tap_random) {
                                    currentStep = 3
                                }

                            }
                            .border(
                                BorderStroke(2.dp, Color(105, 205, 216)),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }
            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(stringResource(id = R.string.tap_lemon_to_drink),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.lemon_drink),
                        contentDescription = stringResource(id = R.string.tap_lemon_to_drink),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 4
                            }
                            .border(
                                BorderStroke(2.dp, Color(105, 205, 216)),
                                shape = RoundedCornerShape(4.dp)
                            ))
                }
            }
            4 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(stringResource(id = R.string.tap_empty),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.lemon_restart),
                        contentDescription = stringResource(id = R.string.glass_of_lemonade),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 1
                                cnt_tap = 0
                            }
                            .border(
                                BorderStroke(2.dp, Color(105, 205, 216)),
                                shape = RoundedCornerShape(4.dp)
                            ))
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun ImageAndText() {
    LemonApp()
}