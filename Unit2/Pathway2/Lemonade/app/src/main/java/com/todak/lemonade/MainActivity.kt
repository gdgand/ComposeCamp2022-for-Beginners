package com.todak.lemonade

import android.os.Bundle
import android.util.Log
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
import com.todak.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LemonApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonApp()
    }
}


@Composable
fun LemonApp() {
    Log.d("test33", "LemonApp start")

    // currentStep 값이 변경되 LemonTextAndImage() 를 호출
//    var currentStep by remember { mutableStateOf(1) }
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Log.d("test33", "currentStep: $currentStep")
    Log.d("test33", "squeezeCount: $squeezeCount")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    R.string.lemon_tree,
                    R.drawable.lemon_tree,
                    currentStep.toString(),
                    onImageClick = {
                        squeezeCount = (2..4).random()
                        currentStep = 2
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    R.string.lemon,
                    R.drawable.lemon_squeeze,
                    currentStep.toString(),
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0)
                            currentStep = 3
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    R.string.glass_of_lemonade,
                    R.drawable.lemon_drink,
                    currentStep.toString(),
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    R.string.empty_glass,
                    R.drawable.lemon_restart,
                    currentStep.toString(),
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
    Log.d("test33", "LemonApp End")
}

@Composable
fun LemonTextAndImage(
    stringResId: Int,
    drawableResId: Int,
    contentDescriptionResId: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("test33", "LemonTextAndImage start")

        Text(text = stringResource(stringResId), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = contentDescriptionResId,
            modifier = Modifier
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
    Log.d("test33", "LemonTextAndImage end")

}