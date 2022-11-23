package com.example.namecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.namecard.ui.theme.NameCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NameCard()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NameCardTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = false)
@Composable
fun NameCard() {
    ProfileBlock()
    ContentBlock()
}

@Preview(showBackground = false)
@Composable
fun ProfileBlock(imgSrc: String = "./android_logo.png", fullName: String = "John Doe", title: String = "title") {
    val profileImg = painterResource(id = R.drawable.android_logo)
    Column() {
        Row() {
            Image(painter = profileImg, contentDescription = "profile image")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ContentBlock() {
    
}