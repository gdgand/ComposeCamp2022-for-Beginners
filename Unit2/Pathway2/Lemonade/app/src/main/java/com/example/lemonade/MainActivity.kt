package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember {
        mutableStateOf(2)
    }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    R.string.lemon_tree_desc,
                    R.drawable.lemon_tree,
                    R.string.lemon_tree_desc,
                    onImageClick = {
                        // Update to next step
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                    LemonTextAndImage(
                        R.string.lemon_squeeze,
                        R.drawable.lemon_squeeze,
                        R.string.lemon_squeeze_desc,
                        onImageClick = {
                            // Update to next step
                            squeezeCount--
                            if(squeezeCount == 0){
                                currentStep = 3
                            }
                        }
                    )
                }
            3 -> {
                LemonTextAndImage(
                    R.string.lemon_drink,
                    R.drawable.lemon_drink,
                    R.string.lemon_drink_desc,
                    onImageClick = {
                        // Update to next step
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    R.string.lemon_restart,
                    R.drawable.lemon_restart,
                    R.string.lemon_restart_desc,
                    onImageClick = {
                        // Update to next step
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable (
                    onClick = onImageClick
                )
                .border(
                    width = 2.dp,
                    color = Color(105, 205, 216),
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