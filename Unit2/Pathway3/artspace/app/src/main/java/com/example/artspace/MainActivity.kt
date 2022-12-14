package com.example.artspace

import android.content.ClipDescription
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(24.dp)) {

            Column(modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                when(currentStep) {
                    1 -> {
                        ArtImage(
                            drawableResourceId = R.drawable.first,
                            contentDescriptionResourceId = R.string.first_content_description
                        )
                    }
                    2 -> {
                        ArtImage(
                            drawableResourceId = R.drawable.second,
                            contentDescriptionResourceId = R.string.second_content_description
                        )
                    }
                    3 -> {
                        ArtImage(
                            drawableResourceId = R.drawable.thrid,
                            contentDescriptionResourceId = R.string.third_content_description
                        )
                    }

                    4 -> {
                        ArtImage(
                            drawableResourceId = R.drawable.fourth,
                            contentDescriptionResourceId = R.string.fourth_content_description
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {


                when(currentStep) {
                    1 -> {
                        ArtText(
                            textlabelResourceId = R.string.first_text,
                            textDescription = R.string.first_description
                        )
                    }
                    2 -> {
                        ArtText(
                            textlabelResourceId = R.string.second_text,
                            textDescription = R.string.second_description
                        )
                    }
                    3 -> {
                        ArtText(
                            textlabelResourceId = R.string.third_text,
                            textDescription = R.string.third_descriptione
                        )
                    }

                    4 -> {
                        ArtText(
                            textlabelResourceId = R.string.fourth_text,
                            textDescription = R.string.fourth_description
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(onClick = {
                    if (currentStep == 1) {
                        currentStep = 4
                    }else {
                        currentStep--
                    }
                }, modifier = Modifier.weight(1f)) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    if (currentStep == 4) {
                        currentStep = 1
                    }else {
                        currentStep++
                    }
                }, modifier = Modifier.weight(1f)) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun ArtImage(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
) {
    Column(
   ) {
        Image(
            painter = painterResource( drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()

                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)

            )
    }

}
@Composable
fun ArtText(
    textlabelResourceId: Int,
    textDescription: Int
){
    Column(
        modifier = Modifier
            .wrapContentSize()


            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = textlabelResourceId)
        )
        Text(
            text = stringResource(id = textDescription)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtspaceTheme {
        ArtSpaceApp()
    }
}