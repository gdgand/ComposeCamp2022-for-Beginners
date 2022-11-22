package com.example.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirtydays.ui.theme.ThirtyDaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThirtyDaysApp()
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysApp() {
    Text(text = "Hello")
}



@Preview(name = "OneDayItem", showBackground = true)
@Composable
fun OneDayItemPreview() {
    ThirtyDaysTheme {
        Greeting("Android")
    }
}

@Preview(name = "OneDayItem", showBackground = true)
@Composable
fun OneDayItemPreview() {
    ThirtyDaysTheme {
        Greeting("Android")
    }
}