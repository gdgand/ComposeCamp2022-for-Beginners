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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var stepState by remember { mutableStateOf(1) }
    var imageResource = R.drawable.fruit;
    var title = R.string.title_1;
    var author = R.string.artist_1;
    var year = R.string.year_1
    when (stepState){
        1 -> {
            imageResource = R.drawable.fruit;
            title = R.string.title_1;
            author = R.string.artist_1;
            year = R.string.year_1;
        }
        2 -> {
            imageResource = R.drawable.ship;
            title = R.string.title_2;
            author = R.string.artist_2;
            year = R.string.year_2;
        }
        3 ->{
            imageResource = R.drawable.island;
            title = R.string.title_3;
            author = R.string.artist_3;
            year = R.string.year_3;
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
            Box(modifier.weight(6f)
            ){
            androidx.compose.material.Surface(

                        color= Color.White,
                        shape = RectangleShape,
                        elevation = 12.dp,
                        border = BorderStroke(3.dp, Color.Gray),
                        modifier = Modifier
                            .padding(0.dp, 36.dp)

            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                )
            }
            }
            Surface(color= Color.White,
                shape = RectangleShape,
                elevation = 6.dp,
                modifier = Modifier
                    .weight(2f)
                    .padding(0.dp, 0.dp, 0.dp, 36.dp)


            )
            {
            Column(
                modifier

                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(title),fontSize = 28.sp, fontWeight = FontWeight.Light  )
                Row(
                ) {
                    Text(text = stringResource(author),fontWeight = FontWeight.Bold )
                    Text(text = " (" + stringResource(year) + ")")
                }

            }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                              if(stepState === 1) {
                                  stepState = 3;
                              } else stepState -= 1
                    },
                    modifier
                        .weight(1f)
                        .padding(12.dp, 0.dp, 12.dp, 0.dp)
                ) {
                    Text(text = "Previous", modifier.padding(2.dp))
                }

                Button(
                    onClick = {
                        if(stepState === 3) {
                            stepState = 1;
                        } else stepState += 1
                    },
                    modifier
                        .weight(1f)
                        .padding(12.dp, 0.dp, 12.dp, 0.dp)
                ) {
                    Text(text = "Next" , modifier.padding(2.dp))
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}