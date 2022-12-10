package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.amphibiansapp.ui.AmphibiansApp
import com.example.amphibiansapp.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansTheme {
                AmphibiansApp()
            }
        }
    }
}