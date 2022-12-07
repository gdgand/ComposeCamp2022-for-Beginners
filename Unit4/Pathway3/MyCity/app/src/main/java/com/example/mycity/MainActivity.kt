package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme {
                // A surface container using the 'background' color from the theme
                val windowSize = calculateWindowSizeClass(activity = this)
//                ReplyApp(windowSize = WindowWidthSizeClass.Medium)
                CityApp(windowSize = windowSize.widthSizeClass)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ReplyAppPreview() {
    MyCityTheme {
        CityApp(windowSize = WindowWidthSizeClass.Expanded)
//        ReplyApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
