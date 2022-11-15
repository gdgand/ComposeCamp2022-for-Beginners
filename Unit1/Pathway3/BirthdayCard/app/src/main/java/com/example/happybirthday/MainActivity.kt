package com.example.happybirthday

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BirthdayGreetingWithImage(getString(R.string.happy_birthday_text), getString(R.string.signature_text))
                }
            }
        }
    }
}

@Composable
fun BirthDayGreetingWithText(message: String, from: String) {
    Column() {
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    val image = painterResource(id = R.drawable.cute_hippo_using_birthday_hat_holds_balloon_277370_375_removebg)
    val image1 = painterResource(id = R.drawable.y2020_05_29_05_generated_removebg_preview)
    Box{
        Row(){
            Image(
                painter = image1,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Image(
                painter = image1,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
       Center() {
           Image(
               painter = image,
               contentDescription = null,
               modifier = Modifier
                   .height(height = 370.dp)
                   .fillMaxWidth(),
               contentScale = ContentScale.Crop
           )
       }
        Bottom(
            modifier = Modifier
                .padding(bottom = 32.dp)
        ) {
            BirthDayGreetingWithText(message = message, from = from)
        }
    }
}
@Composable
fun Bottom( modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}
@Composable
fun Center( modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}
@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme() {
        BirthdayGreetingWithImage("Happy birthday Ucok!", "- from Andrew")
    }
}
