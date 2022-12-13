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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val stepState by remember { mutableStateOf(1) }
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            androidx.compose.material.Surface(


                        color= Color.White,
                        shape = RectangleShape,
                        elevation = 12.dp,
                        border = BorderStroke(3.dp, Color.Gray),
                        modifier = Modifier.padding(0.dp, 36.dp )


            ) {
                Image(
                    painter = painterResource(R.drawable.dice_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                )
            }
            Surface(color= Color.White,
                shape = RectangleShape,
                elevation = 6.dp,
                modifier = Modifier.padding(0.dp,0.dp,0.dp, 36.dp )


            )
            {
            Column(
                modifier.fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(R.string.sample_title),fontSize = 28.sp, fontWeight = FontWeight.Light  )
                Row(
                ) {
                    Text(text = stringResource(R.string.sample_artist),fontWeight = FontWeight.Bold )
                    Text(text = " (" + stringResource(R.string.sample_year) + ")")
                }

            }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier.weight(1f).padding(12.dp, 0.dp, 12.dp, 6.dp)
                ) {
                    Text(text = "Previous", modifier.padding(2.dp))
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier.weight(1f).padding(12.dp, 0.dp, 12.dp, 0.dp)
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