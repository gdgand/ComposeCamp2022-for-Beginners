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
    // A surface container using the 'background' color from the theme

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
//            1 -> {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Text(text = stringResource(id = R.string.tap_the_lemon))
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Image(painter = painterResource(id = R.drawable.lemon_tree),
//                        contentDescription = stringResource(id = R.string.lt),
//                        modifier = Modifier
//                            .wrapContentSize()
//                            .clickable { currentStep = 2 }
//                    )
//                }
//            }
            1 -> {
                // Display lemon tree image and ask user to pick a lemon from the tree
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_the_lemon,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lt,
                    onImageClick = {
                        // Update to next step
                        currentStep = 2
                        // Each time a lemon is picked from the tree, get a new random number
                        // between 2 and 4 (inclusive) for the number of times the lemon needs
                        // to be squeezed to turn into lemonade
                        squeezeCount = (2..4).random()
                    }
                )
            }
//
//            2 -> {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Text(text = stringResource(id = R.string.keep_tapping_the))
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.lemon_squeeze),
//                        contentDescription = stringResource(id = R.string.l),
//                        modifier = Modifier.wrapContentSize()
//                    )
//                }
//            }
            2 -> {
                // Display lemon image and ask user to squeeze the lemon
                LemonTextAndImage(
                    textLabelResourceId = R.string.keep_tapping_the,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.l,
                    onImageClick = {
                        // Decrease the squeeze count by 1 for each click the user performs
                        squeezeCount--
                        // When we're done squeezing the lemon, move to the next step
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
//
//            3 -> {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Text(text = stringResource(id = R.string.tap_the_lemonade))
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.lemon_drink),
//                        contentDescription = stringResource(id = R.string.gol),
//                        modifier = Modifier.wrapContentSize()
//                    )
//                }
//            }
            3 -> {
                // Display glass of lemonade image and ask user to drink the lemonade
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_the_lemonade,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.gol,
                    onImageClick = {
                        // Update to next step
                        currentStep = 4
                    }
                )
            }

//
//            4 -> {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Text(text = stringResource(id = R.string.tap_the_empty))
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.lemon_restart),
//                        contentDescription = stringResource(id = R.string.eg),
//                        modifier = Modifier.wrapContentSize()
//                    )
//                }
//            }
            4 -> {
                // Display empty glass image and ask user to start again
                LemonTextAndImage(
                    textLabelResourceId = R.string.tap_the_empty,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.eg,
                    onImageClick = {
                        // Back to starting step
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
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
                .clickable(
                    onClick = onImageClick
                )
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
    LemonadeTheme {
        LemonApp()
    }
}