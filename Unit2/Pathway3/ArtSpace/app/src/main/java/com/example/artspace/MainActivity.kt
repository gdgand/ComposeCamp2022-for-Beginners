package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceImage(drawableResourceId: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(20.dp).height(450.dp),
    ) {
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = null,
            modifier = modifier
                .wrapContentSize()
                .shadow(5.dp)
                .padding(30.dp)
        )
    }
}
@Composable
fun ArtSpaceDesc(
    titleResourceId: Int,
    authorResourceId: Int,
    yearResourceId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(15.dp),
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = stringResource(id = titleResourceId),
                fontSize = 25.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = authorResourceId))
                    }
                    append(stringResource(id = yearResourceId))
                }
            )

        }
    }
}

@Composable
fun DisplayController(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround) {
        Button(
            onClick = onClickPrevious,
            modifier = Modifier.width(150.dp)
        ) {
            Text("Previous")
        }
        Button(
            onClick = onClickNext,
            modifier = Modifier.width(150.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun ArtSpace(
    drawableResourceId: Int,
    titleResourceId: Int,
    authorResourceId: Int,
    yearResourceId: Int,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        ArtSpaceImage(drawableResourceId)
        ArtSpaceDesc(titleResourceId, authorResourceId, yearResourceId)
    }
}

@Composable
fun ArtSpaceScreen(
    drawableResourceId: Int,
    titleResourceId: Int,
    authorResourceId: Int,
    yearResourceId: Int,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArtSpace(drawableResourceId, titleResourceId, authorResourceId, yearResourceId)
        DisplayController(onClickPrevious, onClickNext)
    }
}

@Composable
fun ArtSpaceApp() {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                ArtSpaceScreen(
                    R.drawable.woman_with_a_parasol,
                    R.string.first_art_title,
                    R.string.first_art_author,
                    R.string.first_art_year,
                    onClickPrevious = {
                        currentStep = 3
                    },
                    onClickNext = {
                        currentStep++
                    }
                )
            }
            2 -> {
                ArtSpaceScreen(
                    R.drawable.the_weeping_woman,
                    R.string.second_art_title,
                    R.string.second_art_author,
                    R.string.second_art_year,
                    onClickPrevious = {
                        currentStep--
                    },
                    onClickNext = {
                        currentStep++
                    }
                )
            }
            3 -> {
                ArtSpaceScreen(
                    R.drawable.the_scream_of_nature,
                    R.string.third_art_title,
                    R.string.third_art_author,
                    R.string.third_art_year,
                    onClickPrevious = {
                        currentStep--
                    },
                    onClickNext = {
                        currentStep = 1
                    }
                )
            }
        }
    }
    
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}