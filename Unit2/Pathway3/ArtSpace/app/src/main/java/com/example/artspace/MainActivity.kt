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
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var images = mutableListOf<Int>(R.drawable.hyukey, R.drawable.homigot,  R.drawable.spacework)
    var titles = mutableListOf<Int>(R.string.hyukey, R.string.homigot, R.string.spacework)
    var descriptions = mutableListOf<Int>(R.string.hyukey_desc, R.string.homigot_desc, R.string.spacework_desc)
    var num by remember { mutableStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = images[num]),
            contentDescription = stringResource(id = descriptions[num]),
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Gray))
                .padding(30.dp)
                .fillMaxWidth(0.7f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text( //artImages.size.toString(), //
            text = stringResource(id = titles[num]),
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = descriptions[num]),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row() {
            Button(
                onClick = {
                    num -= 1
                    if(num < 0) {
                        num = images.size - 1
                    }
                }
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    num += 1
                    if(num >= images.size) {
                        num = 0
                    }
                }
            ) {
                Text(text = "Next")
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