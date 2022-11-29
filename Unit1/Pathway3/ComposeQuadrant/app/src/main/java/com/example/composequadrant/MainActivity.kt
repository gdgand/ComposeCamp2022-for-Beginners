package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantApp()
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ComposeQuadrantApp() {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .fillMaxHeight(1f)
        ) {
            ComposableInfoCard(
                title = "Text Composable",
                description = "Displays text and follows Material Design guidelines.",
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f, true)
            )
            ComposableInfoCard(
                title = "Image Composable",
                description = "Creates a composable that lays out and draws a given Painter class object.",
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f, true)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .fillMaxHeight(1f)
        ) {
            ComposableInfoCard(
                title = "Row Composable",
                description = "A layout composable that places its children in a horizontal sequence.",
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f, true)
            )
            ComposableInfoCard(
                title = "Column Composable",
                description = "A layout composable that places its children in a vertical sequence.",
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f, true)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .then(modifier)
            .fillMaxHeight(1f)
            .background(color = backgroundColor)
            .padding(all = 16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = description,
            fontSize = TextUnit.Unspecified
        )
    }
}