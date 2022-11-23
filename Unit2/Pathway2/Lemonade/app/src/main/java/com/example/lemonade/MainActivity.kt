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
    var count by remember {
        mutableStateOf(0)
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textLabelId = R.string.article1,
                    imageId = R.drawable.lemon_tree,
                    descriptionId = R.string.tree,
                    onImageClick = {
                        currentStep = 2
                        count = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonTextAndImage(
                    textLabelId = R.string.article2,
                    imageId = R.drawable.lemon_squeeze,
                    descriptionId = R.string.Lemon,
                    onImageClick = {
                        count--
                        if (count == 0) {
                            currentStep = 3
                        }
                    }
                )

            }

            3 -> {
                LemonTextAndImage(
                    textLabelId = R.string.article3,
                    imageId = R.drawable.lemon_drink,
                    descriptionId = R.string.Glass,
                    onImageClick = {
                        currentStep = 4
                    }
                )

            }

            4 -> {
                LemonTextAndImage(
                    textLabelId = R.string.article4,
                    imageId = R.drawable.lemon_restart,
                    descriptionId = R.string.Empty,
                    onImageClick = {
                        currentStep = 1
                    }
                )

            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelId: Int,
    imageId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = textLabelId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = descriptionId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(63, 81, 181, 255)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}