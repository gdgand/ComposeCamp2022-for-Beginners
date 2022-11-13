package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                ComposeQuadrantApp()
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    val cards = arrayOf(
        CardData("Text composable", "Displays text and follows Material Design guidelines.", Color.Green),
        CardData("Image composable", "Creates a composable that lays out and draws a given Painter class object.", Color.Yellow),
        CardData("Row composable", "A layout composable that places its children in a horizontal sequence.", Color.Cyan),
        CardData("Column composable", "A layout composable that places its children in a vertical sequence.", Color.LightGray)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposableInfoCard(cards[0].title, cards[0].description, cards[0].backgroundColor, modifier = Modifier.weight(1f))
            ComposableInfoCard(cards[1].title, cards[1].description, cards[1].backgroundColor, modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposableInfoCard(cards[2].title, cards[2].description, cards[2].backgroundColor, modifier = Modifier.weight(1f))
            ComposableInfoCard(cards[3].title, cards[3].description, cards[3].backgroundColor, modifier = Modifier.weight(1f))
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
        modifier = modifier
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
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}

data class CardData(val title: String, val description: String, val backgroundColor: Color)