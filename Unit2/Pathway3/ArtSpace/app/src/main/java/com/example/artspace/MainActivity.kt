package com.example.artspace

import android.os.Bundle
import android.view.Surface
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
import androidx.compose.ui.text.font.FontStyle
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
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> Column(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ArtImageAndTextAndButton(
                    drawableResourceId = R.drawable.dice_1,
                    textLabelResourceId = R.string.art_title_1,
                    contentDescriptionResourceId = R.string.art_description_1,
                    onPreviousButtonClick = {
                        currentStep = 4
                    },
                    onNextButtonClick = {
                        currentStep = 2
                    }
                )
            }
            2 -> Column(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ArtImageAndTextAndButton(
                    drawableResourceId = R.drawable.dice_2,
                    textLabelResourceId = R.string.art_title_2,
                    contentDescriptionResourceId = R.string.art_description_2,
                    onPreviousButtonClick = {
                        currentStep = 1
                    },
                    onNextButtonClick = {
                        currentStep = 3
                    }
                )
            }
            3 -> Column(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ArtImageAndTextAndButton(
                    drawableResourceId = R.drawable.dice_3,
                    textLabelResourceId = R.string.art_title_3,
                    contentDescriptionResourceId = R.string.art_description_3,
                    onPreviousButtonClick = {
                        currentStep = 2
                    },
                    onNextButtonClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> Column(
                modifier = Modifier.padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ArtImageAndTextAndButton(
                    drawableResourceId = R.drawable.dice_4,
                    textLabelResourceId = R.string.art_title_4,
                    contentDescriptionResourceId = R.string.art_description_4,
                    onPreviousButtonClick = {
                        currentStep = 3
                    },
                    onNextButtonClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun ArtImageAndTextAndButton(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = null,
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
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(contentDescriptionResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Button(
                onClick = onPreviousButtonClick,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = stringResource(id = R.string.previous_button), fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(22.dp))
            Button(
                onClick = onNextButtonClick,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = stringResource(id = R.string.next_button), fontSize = 24.sp)
            }
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