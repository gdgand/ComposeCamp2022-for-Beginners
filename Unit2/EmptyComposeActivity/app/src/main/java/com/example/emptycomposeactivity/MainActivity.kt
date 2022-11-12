package com.example.emptycomposeactivity

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
import com.example.emptycomposeactivity.ui.theme.EmptyComposeActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyComposeActivityTheme {
                // A surface container using the 'background' color from the theme
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                ImageAndText(
                    textId = R.string.lemon_tree,
                    imageId = R.drawable.lemon_tree,
                    descId = R.string.lemon_tree_desc,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                ImageAndText(
                    textId = R.string.lemon,
                    imageId = R.drawable.lemon_squeeze,
                    descId = R.string.lemon_desc,
                    onImageClick = {
                        if (--squeezeCount == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                ImageAndText(
                    textId = R.string.glass_of_lemonade,
                    imageId = R.drawable.lemon_drink,
                    descId = R.string.glass_of_lemonade_desc,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                ImageAndText(
                    textId = R.string.empty_glass,
                    imageId = R.drawable.lemon_restart,
                    descId = R.string.empty_glass_desc,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }

    }
}

@Composable
fun ImageAndText(
    textId: Int,
    imageId: Int,
    descId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = textId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = descId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(BorderStroke(2.dp, Color(105, 205, 216)), shape = RoundedCornerShape(4.dp))
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EmptyComposeActivityTheme() {
        LemonApp()
    }
}