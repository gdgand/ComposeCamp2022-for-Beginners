package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(){
    var step by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(step){
            1 -> {
                LemonadeDescriptionWithImage(
                    textResourceId = R.string.lemon_tree_select,
                    ImageResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClick = {
                        step = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                LemonadeDescriptionWithImage(
                    textResourceId = R.string.lemon_squeeze,
                    ImageResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClick = {
                        squeezeCount--
                        if(squeezeCount == 0){
                            step = 3
                        }
                    })
            }
            3 -> {
                LemonadeDescriptionWithImage(
                    textResourceId = R.string.lemonade_drink,
                    ImageResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.glass_content_description,
                    onImageClick = {
                        step = 4
                    })
            }
            4 -> {
                LemonadeDescriptionWithImage(
                    textResourceId = R.string.restart_lemonade,
                    ImageResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClick = {
                        step = 1
                    })
            }
        }
    }
    
}

@Composable
fun LemonadeDescriptionWithImage(
    textResourceId : Int,
    ImageResourceId : Int,
    contentDescriptionResourceId : Int,
    onImageClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(ImageResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                )
                .padding(16.dp)
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