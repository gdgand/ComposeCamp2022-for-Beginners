package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        var currentStep by remember { mutableStateOf(1) }

        val imageResource = when (currentStep) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val artTitleResource = when (currentStep) {
            1 -> R.string.dice_1
            2 -> R.string.dice_2
            3 -> R.string.dice_3
            4 -> R.string.dice_4
            5 -> R.string.dice_5
            else -> R.string.dice_6
        }

        Column() {
            ArtImage(imageResource, modifier = Modifier.weight(0.7f))
            Spacer(modifier = Modifier.height(16.dp))
            ArtCaption(artTitleResource, R.string.artist_name, "1")
            Spacer(modifier = Modifier.height(16.dp))
            PreviousAndNextButtonRow(onClickPreviousButton = {
                if (currentStep == 1) currentStep = 6
                else currentStep--
            }, onClickNextButton = {
                if (currentStep == 6) currentStep = 1
                else currentStep++
            })
        }
    }
}

@Composable
fun ArtImage(@DrawableRes imageResource: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(2.dp, Color.Gray, RectangleShape)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null
        )
    }
}

@Composable
fun ArtCaption(@StringRes titleResource: Int, @StringRes artistNameResource: Int, year: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(stringResource(id = titleResource), fontSize = 24.sp)
        Row {
            Text(stringResource(artistNameResource), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(4.dp))
            Text("(${year})")
        }
    }
}

@Composable
fun PreviousAndNextButtonRow(onClickPreviousButton: () -> Unit, onClickNextButton: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(onClick = onClickPreviousButton, modifier = Modifier.weight(0.4f)) {
            Text(stringResource(id = R.string.previous))
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Button(onClick = onClickNextButton, modifier = Modifier.weight(0.4f)) {
            Text(stringResource(id = R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}