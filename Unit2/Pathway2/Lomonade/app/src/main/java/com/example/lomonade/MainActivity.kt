package com.example.lomonade

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
import com.example.lomonade.ui.theme.LomonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LomonadeTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTemplate(
                    textId = R.string.tap_lemon_tree,
                    imageId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.lemonade_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                Text(text = "Left lemons $squeezeCount")
                LemonTemplate(
                    textId = R.string.keep_tapping,
                    imageId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.lemon,
                    onImageClick = {
                        squeezeCount = squeezeCount - 1 //squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                        //currentStep = squeezeCount == 0 ? 3 : currentStep
                    })
            }
            3 -> {

                LemonTemplate(
                    textId = R.string.tap_lemonade,
                    imageId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.glass_of_loemonade,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                LemonTemplate(
                    textId = R.string.keep_empty_glass,
                    imageId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.empty_glass,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun LemonTemplate(
    textId: Int,
    imageId: Int,
    contentDescriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =  Modifier.fillMaxSize()
    ){
        Text(text = stringResource(textId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
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
        LomonadeTheme {
            LemonApp()
        }
}