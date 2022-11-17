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
    var currentStep by remember {
        mutableStateOf(1)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_lemon,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClick = { currentStep = 2})
            }
            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_keep,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClick = { currentStep = 3})
            }
            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_lemonade,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.glass_of_lemonade_content_description,
                    onImageClick = { currentStep = 4})
            }
            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_empty,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClick = { currentStep = 1})
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = Modifier
                .clickable(onClick = onImageClick)
                .padding(16.dp)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .wrapContentSize()
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