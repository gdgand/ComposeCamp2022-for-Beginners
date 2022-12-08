package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var currentStep by remember { mutableStateOf(1) }
    var squeezeTimes by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> LemonadeStepItem(
                imageResource = R.drawable.lemon_tree,
                imageDescription = stringResource(id = R.string.tree_image_description),
                description = stringResource(id = R.string.tree_description),
                modifier = Modifier
                    .fillMaxSize(),
                action = {
                    currentStep = 2
                    squeezeTimes = (2..4).random()
                }
            )
            2 -> LemonadeStepItem(
                imageResource = R.drawable.lemon_squeeze,
                imageDescription = stringResource(id = R.string.squeeze_image_description),
                description = stringResource(id = R.string.squeeze_description),
                modifier = Modifier
                    .fillMaxSize(),
                action = {
                    squeezeTimes -= 1

                    if(squeezeTimes == 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonadeStepItem(
                imageResource = R.drawable.lemon_drink,
                imageDescription = stringResource(id = R.string.drink_image_description),
                description = stringResource(id = R.string.drink_description),
                modifier = Modifier.fillMaxSize(),
                action = {
                    currentStep = 4
                }
            )
            else -> LemonadeStepItem(
                imageResource = R.drawable.lemon_restart,
                imageDescription = stringResource(id = R.string.restart_image_description),
                description = stringResource(id = R.string.restart_description),
                modifier = Modifier.fillMaxSize(),
                action = {
                    currentStep = 1
                }
            )
        }
    }
}

@Composable
fun LemonadeStepItem(
    imageResource: Int,
    imageDescription: String,
    description: String,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(description)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painterResource(id = imageResource),
            contentDescription = imageDescription,
            modifier = Modifier.clickable {
                action()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}