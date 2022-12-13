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
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                "Text composable",
                "Displays text and follows Material Design guidelines.",
                Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                "Image composable",
                "Creates a composable that lays out and draws a given Painter class object.",
                Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                "Row composable",
                "A layout composable that places its children in a horizontal sequence.",
                Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                "Column composable",
                "A layout composable that places its children in a vertical sequence.",
                Color.LightGray,
                modifier = Modifier.weight(1f)
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
        modifier = modifier.fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )

    {

        Text(text = title, fontWeight = FontWeight.Bold ,  modifier = Modifier.padding(bottom = 16.dp))
        Text(text = description,  textAlign = TextAlign.Justify)

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantApp()
}