package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(){
    var currentStep by remember {
        mutableStateOf(1)
    }
    when(currentStep){
        1 -> {
            ArtSpaceRow(
                imageResourceId = R.drawable.christmas_6902574_960_720,
                contentDescriptionId = R.string.christmas_description,
                textTitleResourceId = R.string.christmas_tree_title,
                textDescriptionResourceId = R.string.christmas_tree_artist,
                previousButton = { currentStep = 4 },
                nextButton = { currentStep++ })
        }
        2 -> {
            ArtSpaceRow(
                imageResourceId = R.drawable.blueberries_539134_960_720,
                contentDescriptionId = R.string.blueberries_description,
                textTitleResourceId = R.string.blueberries_title,
                textDescriptionResourceId = R.string.blueberries_artist,
                previousButton = { currentStep-- },
                nextButton = { currentStep++ })
        }
        3 -> {
            ArtSpaceRow(
                imageResourceId = R.drawable.cereal_1887237_960_720,
                contentDescriptionId = R.string.cereal_description,
                textTitleResourceId = R.string.cereal_title,
                textDescriptionResourceId = R.string.cereal_artist,
                previousButton = { currentStep-- },
                nextButton = { currentStep++ })
        }
        4 -> {
            ArtSpaceRow(
                imageResourceId = R.drawable.macarons_5690175_960_720,
                contentDescriptionId = R.string.macarons_description,
                textTitleResourceId = R.string.macarons_title,
                textDescriptionResourceId = R.string.macarons_artist,
                previousButton = { currentStep-- },
                nextButton = { currentStep = 1 })
        }
    }

}

@Composable
fun ArtSpaceRow(
    imageResourceId : Int,
    contentDescriptionId : Int,
    textTitleResourceId : Int,
    textDescriptionResourceId : Int,
    previousButton : () -> Unit,
    nextButton : () -> Unit,
    modifier: Modifier= Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        ImageWithFrame(imageResourceId = imageResourceId, contentDescriptionId = contentDescriptionId)
        TitleAndArtist(textTitleResourceId = textTitleResourceId, textDescriptionResourceId = textDescriptionResourceId)
        Buttons(previousButton, nextButton)
    }
}

@Composable
fun Buttons(
    previousButton : () -> Unit,
    nextButton : () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.padding(7.dp)
    ){
        Button(
            onClick = { previousButton() },
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ){
            Text(text = "Previous")
        }
        Button(
            onClick = { nextButton() },
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(text = "Next")
        }

    }
}

@Composable
fun TitleAndArtist(
    textTitleResourceId : Int,
    textDescriptionResourceId : Int,
    modifier: Modifier = Modifier
){
    Surface(
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = textTitleResourceId),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = textDescriptionResourceId),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun ImageWithFrame(
    imageResourceId: Int,
    contentDescriptionId: Int,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier.height(500.dp),
        elevation = 3.dp,
        border = BorderStroke(3.dp, Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = modifier.padding(30.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}