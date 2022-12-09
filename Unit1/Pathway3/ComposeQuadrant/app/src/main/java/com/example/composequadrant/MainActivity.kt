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
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(id = R.string.first_title),
                description = stringResource(id = R.string.first_description),
                backgroundColor = colorResource(id = R.color.purple_200),
                Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.second_title),
                description = stringResource(id = R.string.second_description),
                backgroundColor = colorResource(id = R.color.purple_700),
                Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(id = R.string.third_title),
                description = stringResource(id = R.string.third_description),
                backgroundColor = colorResource(id = R.color.teal_200),
                Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.fourth_title),
                description = stringResource(id = R.string.first_description),
                backgroundColor = colorResource(id = R.color.teal_700),
                Modifier.weight(1f)
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
    Surface(color = backgroundColor, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = description, textAlign = TextAlign.Justify)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            ComposeQuadrantApp()
        }
    }
}