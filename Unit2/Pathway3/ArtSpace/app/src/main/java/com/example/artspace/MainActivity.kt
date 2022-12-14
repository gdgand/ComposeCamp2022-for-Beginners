package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

val imageData = listOf(
    mapOf(
        "image" to R.drawable.a,
        "name" to "이미지 11111",
        "artist" to "그림작가 111132",
        "year" to "1230"
    ),
    mapOf("image" to R.drawable.b, "name" to "이미지 222", "artist" to "만화작가 2", "year" to "1460"),
    mapOf(
        "image" to R.drawable.c,
        "name" to "이미지 3333333",
        "artist" to "사진작가 33",
        "year" to "1945"
    ),
    mapOf("image" to R.drawable.d, "name" to "이미지 4", "artist" to "영화작가 411", "year" to "2010")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier) {

    var currentIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLayout(currentIndex, Modifier.weight(1f))
        Spacer(Modifier.height(20.dp))
        ContentLayout(currentIndex)
        Spacer(Modifier.height(40.dp))
        ButtonLayout {
            val temp = currentIndex + it
            if (temp >= 0 && temp < imageData.size)
                currentIndex = temp
        }
    }

}

@Composable
fun ImageLayout(currentIndex: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = 16.dp,
    ) {
        Image(
            modifier = modifier.padding(30.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = imageData[currentIndex]["image"] as Int),
            contentDescription = imageData[currentIndex]["name"] as String
        )
    }
}

@Composable
fun ContentLayout(currentIndex: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = 10.dp) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(text = imageData[currentIndex]["name"] as String, fontSize = 22.sp)
            Text(buildAnnotatedString() {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                ) {
                    append(imageData[currentIndex]["artist"] as String)
                }
                withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                    append(" (${imageData[currentIndex]["year"] as String})")
                }
            })
        }
    }
}

@Composable
fun ButtonLayout(modifier: Modifier = Modifier, onChange: (Int) -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            onChange(-1)
        }) {
            Text(text = "Prev")
        }
        Button(onClick = {
            onChange(1)
        }) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        MainView()
    }
}