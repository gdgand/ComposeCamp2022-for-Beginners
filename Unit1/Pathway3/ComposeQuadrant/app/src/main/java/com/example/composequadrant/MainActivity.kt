package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme() {
                ComposeQuadrant()
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    Row(Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {
            ComposeCard(
                "Text composable",
                "Displays text and follows Material Design guidelines.",
                Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                "Row composable",
                "A layout composable that places its children in a horizontal sequence.",
                Color.Cyan,
                modifier = Modifier.weight(1f)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            ComposeCard(
                "Image composable",
                "Creates a composable that lays out and draws a given Painter class object.",
                Color.Yellow,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                "Column composable",
                "A layout composable that places its children in a vertical sequence.",
                Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeCard(
    title: String,
    content: String,
    backgroundColor: Color,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}
