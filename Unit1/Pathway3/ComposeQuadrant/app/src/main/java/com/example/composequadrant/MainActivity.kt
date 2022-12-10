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
import androidx.compose.ui.text.font.FontStyle
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
                Surface(color = MaterialTheme.colors.background) {
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                stringResource(id = R.string.first_title),
                stringResource(id = R.string.first_description),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Green)
            )
            ComposableInfoCard(
                stringResource(id = R.string.second_title),
                stringResource(id = R.string.second_description),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                stringResource(id = R.string.third_title),
                stringResource(id = R.string.third_description),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Cyan)
            )
            ComposableInfoCard(
                stringResource(id = R.string.fourth_title),
                stringResource(id = R.string.fourth_description),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
    ComposeQuadrantApp()
}