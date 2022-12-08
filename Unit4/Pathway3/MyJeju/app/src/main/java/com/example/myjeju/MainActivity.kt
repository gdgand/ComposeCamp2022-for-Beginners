package com.example.myjeju

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myjeju.ui.theme.MyJejuTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.myjeju.ui.MyJejuApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJejuTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                MyJejuApp(
                    windowSize = windowSize.widthSizeClass
                )
            }
        }
    }
}
