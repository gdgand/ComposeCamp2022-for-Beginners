package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
                LemonadeApp()
            }
        }
    }
}

@Composable
fun TextAndImageButton(modifier: Modifier = Modifier,
                       imageResource: Int, textResource: Int,
                       imageDescTextRes: Int,
                       onImageClicked: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(imageDescTextRes),
            modifier = Modifier
                .clickable ( onClick = onImageClicked )
                .border(
                    width = 2.dp,
                    color = Color(105, 205, 216),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}

@Composable
fun LemonadeApp(){
    var currentStep by remember { mutableStateOf(1) }
    var count by remember { mutableStateOf(0) }
    when(currentStep) {
        1 -> TextAndImageButton(
            imageResource = R.drawable.lemon_tree,
            textResource = R.string.lemon_tree_instruction,
            imageDescTextRes = R.string.lemon_tree_content_description,
            onImageClicked = {
                currentStep = 2
                count = (2..4).random()
            }
        )
        2 -> TextAndImageButton(
            imageResource = R.drawable.lemon_squeeze,
            textResource = R.string.lemon_instruction,
            imageDescTextRes = R.string.lemon_content_description,
            onImageClicked = {
                count--
                if(count==0){
                    currentStep = 3
                }
            }
        )
        3 -> TextAndImageButton(
            imageResource = R.drawable.lemon_drink,
            textResource = R.string.lemonade_instruction,
            imageDescTextRes = R.string.lemonade_content_description,
            onImageClicked = { currentStep = 4}
        )
        else -> TextAndImageButton(
            imageResource = R.drawable.lemon_restart,
            textResource = R.string.glass_instruction,
            imageDescTextRes = R.string.glass_content_description,
            onImageClicked = { currentStep = 1}
        )
    }
}

@Preview
@Composable
fun LemonadeAppPreview(){
    LemonadeTheme {
        LemonadeApp()
    }
}