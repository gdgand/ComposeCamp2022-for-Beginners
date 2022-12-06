package com.example.lemonade


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
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

    var squeezeCount by remember { mutableStateOf(0) }
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
                    Text(text = stringResource(R.string.step_one))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.step_one_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 2
                                squeezeCount = (2..4).random()
                            })
                }
            }
            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(R.string.step_two))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.step_two_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                squeezeCount--
                                if (squeezeCount == 0) {
                                    currentStep = 3
                                }
                            })
                }
            }
            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(R.string.step_three))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.step_three_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 4
                            })
                }
            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(R.string.step_four))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.step_four_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 1
                            })
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(name: String, onStartClicked: () -> Unit){
    Column{
        Text(text = "Welcome $name!")
        Button(
            onClick = onStartClicked
        ){
            Text("Start")
        }
    }
}

@Composable
fun DefaultPreview(){
    LemonadeTheme{
        LemonApp()
    }
}