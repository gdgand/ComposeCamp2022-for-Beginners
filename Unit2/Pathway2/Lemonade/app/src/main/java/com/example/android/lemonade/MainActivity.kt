package com.example.android.lemonade

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
import com.example.android.lemonade.ui.theme.LemonadeTheme

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
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    // Number of times the lemon needs to be squeezed to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(0) }


    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                // Display lemon tree image and ask user to pick a lemon from the tree
                TextAndClickableImageWelcomeScreen(
                    textResourceId = R.string.lemon_select,
                    imageDrawableResourceId = R.drawable.lemon_tree,
                    imageContentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClicked = {
                        // Update to next step
                        currentStep = 2
                        // Each time a lemon is picked from the tree, get a new random number
                        // between 2 and 4 (inclusive) for the number of times the lemon needs
                        // to be squeezed to turn into lemonade
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                // Display lemon image and ask user to squeeze the lemon
                TextAndClickableImageWelcomeScreen(
                    textResourceId = R.string.lemon_squeeze,
                    imageDrawableResourceId = R.drawable.lemon_squeeze,
                    imageContentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClicked = {
                        // Decrease the squeeze count by 1 for each click the user performs
                        squeezeCount--
                        // When we're done squeezing the lemon, move to the next step
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                // Display glass of lemonade image and ask user to drink the lemonade
                TextAndClickableImageWelcomeScreen(
                    textResourceId = R.string.lemon_drink,
                    imageDrawableResourceId = R.drawable.lemon_drink,
                    imageContentDescriptionResourceId = R.string.lemonade_content_description,
                    onImageClicked = {
                        // Update to next step
                        currentStep = 4
                    }
                )
            }
            4 -> {
                // Display empty glass image and ask user to start again
                TextAndClickableImageWelcomeScreen(
                    textResourceId = R.string.lemon_empty_glass,
                    imageDrawableResourceId = R.drawable.lemon_restart,
                    imageContentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClicked = {
                        // Back to starting step
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun TextAndClickableImageWelcomeScreen(
    textResourceId: Int,
    imageDrawableResourceId: Int,
    imageContentDescriptionResourceId: Int,
    onImageClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            painter = painterResource(imageDrawableResourceId),
            contentDescription = stringResource(imageContentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClicked
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