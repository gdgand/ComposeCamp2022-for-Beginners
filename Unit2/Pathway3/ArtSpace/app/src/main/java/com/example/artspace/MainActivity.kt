package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
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
    var idx by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .border(width = 1.dp, color = Color.Gray),
            elevation = 5.dp
        ) {
            Image(painterResource(id =
            when(idx) {
                0 -> R.drawable.ic_launcher_background
                1 -> R.drawable.ic_launcher_foreground
                2 -> R.drawable.ic_launcher_background
                3 -> R.drawable.ic_launcher_foreground
                else -> R.drawable.ic_launcher_background
            }
            ), contentDescription = null, modifier = Modifier.padding(30.dp))
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), elevation = 5.dp
        ) {
            Column {
                Text(text = "Still Life of Blue Rose and Other Flowers", fontSize = 30.sp)
                Row() {
                    Text(fontWeight = FontWeight.Bold, text = "Owen Scoot", modifier = Modifier.padding(end=10.dp))
                    Text("(2021)", color = Color.Gray)
                }
            }
        }
        Row(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {if(idx>0){idx--} }, modifier=Modifier.weight(1f)) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Button(onClick = {if(idx<3){idx++} }, modifier = Modifier.weight(1f)) {
            Text("Next")
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