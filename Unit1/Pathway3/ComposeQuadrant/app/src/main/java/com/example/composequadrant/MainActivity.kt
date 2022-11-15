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
    Column() {
        Row(Modifier.weight(1f)) {
            Column(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.text_composable),
                    description = stringResource(R.string.text_composable_desc),
                    backgroundColor = Color.Green
                )
            }
            Column(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.image_composable),
                    description = stringResource(R.string.image_composable_desc),
                    backgroundColor = Color.Yellow
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Column(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.row_composable),
                    description = stringResource(R.string.row_composable_desc),
                    backgroundColor = Color.Cyan
                )
            }
            Column(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.column_composable),
                    description = stringResource(R.string.column_composable_desc),
                    backgroundColor = Color.LightGray
                )
            }
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background( color = backgroundColor )
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
        Surface(color = MaterialTheme.colors.background) {
            ComposeQuadrantApp()
        }

    }
}