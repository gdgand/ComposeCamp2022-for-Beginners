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

                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
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
                LemonadeTextAndImage(
                    textLabelResource = R.string.lemon_tree_description,
                    descriptionResource = R.string.lemon_tree,
                    drawableResource = R.drawable.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                LemonadeTextAndImage(
                    textLabelResource = R.string.lemon_description,
                    descriptionResource = R.string.lemon,
                    drawableResource = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0)
                            currentStep = 3
                    })
            }
            3 -> {
                LemonadeTextAndImage(
                    textLabelResource = R.string.glass_of_lemonade_description,
                    descriptionResource = R.string.glass_of_lemonade,
                    drawableResource = R.drawable.lemon_drink,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            else -> {
                LemonadeTextAndImage(
                    textLabelResource = R.string.empty_glass_description,
                    descriptionResource = R.string.empty_glass,
                    drawableResource = R.drawable.lemon_restart,
                    onImageClick = { currentStep = 1 })
            }
        }
    }

}


@Composable
fun LemonadeTextAndImage(
    textLabelResource: Int,
    descriptionResource: Int,
    drawableResource: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = textLabelResource), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(

            painter = painterResource(id = drawableResource),
            contentDescription = stringResource(id = descriptionResource),
            modifier = Modifier
                .border(
                    BorderStroke(4.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = onImageClick)
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