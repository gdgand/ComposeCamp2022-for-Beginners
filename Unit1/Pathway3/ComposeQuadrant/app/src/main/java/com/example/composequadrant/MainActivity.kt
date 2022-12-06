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
import androidx.compose.ui.res.colorResource
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

            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column() {
        Row() { }
        Row() { }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = title,

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
        val first = ComposableInfoCard(
            title = stringResource(R.string.first_title),
            description = stringResource(R.string.first_description),
            backgroundColor = colorResource(R.color.)
        )
        val second = ComposableInfoCard(
            title = stringResource(R.string.second_title),
            description = stringResource(R.string.second_description)
        )
        val third = ComposableInfoCard(
            title = stringResource(R.string.third_title),
            description = stringResource(R.string.third_description)
        )
        val fourth = ComposableInfoCard(
            title = stringResource(R.string.fourth_title),
            description = stringResource(R.string.fourth_description)
        )
    }
}