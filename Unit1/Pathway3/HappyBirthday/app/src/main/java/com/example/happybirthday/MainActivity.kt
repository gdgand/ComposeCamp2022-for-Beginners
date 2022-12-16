package com.example.happybirthday

import android.os.Bundle
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
                Surface( color = MaterialTheme.colors.background  ) {
                    BirthdayGreetingWithImage( getString(R.string.happy_birthday_text), getString(R.string.signature_text))
                }
            }
        }
    }
}
@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {

    val image = painterResource(R.drawable.androidparty)
    Box{
        BirthdayGreetingWithText(message = message, from = from)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier   //동작 추가 
                    .fillMaxHeight()  //사용가능한 최대높이,너비 
                    .fillMaxWidth(),
            contentScale = ContentScale.Crop //상응하는 화면에 맡게 가로 세로 비율 조정
        )
        BirthdayGreetingWithText(message = message, from = from)
    }

}



@Composable
fun BirthdayGreetingWithText(message: String, from:String) {
    Column(){
        Text(
            text = message,
            fontSize=36.sp,
            modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = from,
            fontSize=24.sp,
            modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        BirthdayGreetingWithImage( "Happy Birthday Sam!", "- from Emma")
        //BirthdayGreetingWithText( "Happy Birthday Sam!", "-from Emma")
    }
}