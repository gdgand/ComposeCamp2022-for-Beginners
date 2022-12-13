package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme() {
                ArtApp()
            }
        }
    }
}

@Composable
fun ArtApp(){
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    // Number of times the lemon needs to be squeezed to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                // Display lemon tree image and ask user to pick a lemon from the tree
                ArtTextAndImage(
                    textLabelResourceId = R.string.dog_img,
                    drawableResourceId = R.drawable.dog_img,
                    contentDescriptionResourceId = R.string.dog_descript,
                    contentDescriptionDetails = R.string.dog_descript,
                )
            }
            2 -> {
                // Display lemon image and ask user to squeeze the lemon
                ArtTextAndImage(
                    textLabelResourceId = R.string.cat_img,
                    drawableResourceId = R.drawable.cat_img,
                    contentDescriptionResourceId = R.string.cat_descript,
                    contentDescriptionDetails = R.string.cat_descript,
                )
            }
            3 -> {
                // Display glass of lemonade image and ask user to drink the lemonade
                ArtTextAndImage(
                    textLabelResourceId = R.string.penguin_img,
                    drawableResourceId = R.drawable.penguin_img,
                    contentDescriptionResourceId = R.string.penguin_descript,
                    contentDescriptionDetails = R.string.penguin_descript,
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ArtTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    contentDescriptionDetails: Int,
    modifier: Modifier = Modifier
) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result) {
        1 -> R.drawable.dog_img
        2 -> R.drawable.cat_img
        3 -> R.drawable.penguin_img
        else -> R.drawable.dog_img
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 32.sp
        )
        Text(
            text = stringResource(contentDescriptionDetails),
            fontSize = 18.sp
        )
        Row() {
            Button(onClick = {
                result++
            }) {
                Text(stringResource(R.string.next))
                PaddingValues(
                    horizontal = 3.dp
                )
            }
            Button(onClick = {
                result--
            }) {
                Text(stringResource(R.string.previous))
                PaddingValues(
                    horizontal = 3.dp
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
@Preview
fun ArtPreview() {
    ArtSpaceTheme() {
        ArtApp()
    }
}