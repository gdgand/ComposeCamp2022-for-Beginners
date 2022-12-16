package com.example.android.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContent {
           HappyBirthdayTheme {
               // A surface container that uses the 'background' color from the theme
               Surface(color = MaterialTheme.colors.background) {
                   BirthdayGreetingWithText( "Happy Birthday Sam!", "- from Emma")
               }
           }
       }
   }
}

@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
   Column {
       Text(
           text = message,
           fontSize = 36.sp,
       )
       Text(
           text = from,
           fontSize = 24.sp,
       )
   }
}

@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview() {
   HappyBirthdayTheme {
       BirthdayGreetingWithText( "Happy Birthday Sam!", "- from Emma")
   }
}