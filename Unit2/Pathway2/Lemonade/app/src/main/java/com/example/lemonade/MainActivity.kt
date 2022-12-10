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
            LemonadeApp()
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(0) }

    Surface(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ){
        when (currentStep) {
            1 -> {
                LemonWithTextImage(
                    titleResourceId = R.string.lemon_tree_title,
                    drawableResourceId = R.drawable.lemon_tree,
                    descriptionResourceId = R.string.lemon_tree_desc,
                    onImageClick = {
                        currentStep = 2
                        squeeze = (2..4).random()
                    })
            }
            2 -> {
                LemonWithTextImage(
                    titleResourceId = R.string.lemon_title,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    descriptionResourceId = R.string.lemon_desc,
                    onImageClick = {
                        squeeze--
                        if (squeeze == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                LemonWithTextImage(
                    titleResourceId = R.string.glass_of_lemon_title,
                    drawableResourceId = R.drawable.lemon_drink,
                    descriptionResourceId = R.string.glass_of_lemon_desc,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                LemonWithTextImage(
                    titleResourceId = R.string.empty_glass_title,
                    drawableResourceId = R.drawable.lemon_restart,
                    descriptionResourceId = R.string.empty_glass_desc,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun LemonWithTextImage(
    titleResourceId: Int,
    drawableResourceId: Int,
    descriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = descriptionResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = descriptionResourceId),
            modifier = modifier
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }


}