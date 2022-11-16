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
        setContent { }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column() {
        Row() {
            ComposableInfoCard(
                stringResource(R.string.first_title),
                stringResource(R.string.first_description),
                Color.Green
            )
            ComposableInfoCard(
                stringResource(R.string.second_title),
                stringResource(R.string.second_description),
                Color.Yellow
                )
        }
        Row() {
            ComposableInfoCard(
                stringResource(R.string.third_title),
                stringResource(R.string.third_description),
                Color.Cyan
                )
            ComposableInfoCard(
                stringResource(R.string.fourth_title),
                stringResource(R.string.fourth_description),
                Color.LightGray
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
    Column( ) { }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() { }