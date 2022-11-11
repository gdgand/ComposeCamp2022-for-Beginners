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
import androidx.compose.ui.text.TextStyle
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
    Column {
        Row {
            ComposableInfoCard(
                title = stringResource(id = R.string.first_title),
                description = stringResource(id = R.string.first_description),
                backgroundColor = Color.Green
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.second_title),
                description = stringResource(id = R.string.second_description),
                backgroundColor = Color.Yellow
            )
        }

        Row {
            ComposableInfoCard(
                title = stringResource(id = R.string.third_title),
                description = stringResource(id = R.string.third_description),
                backgroundColor = Color.Cyan
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.fourth_title),
                description = stringResource(id = R.string.fourth_description),
                backgroundColor = Color.LightGray
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
//            .width(IntrinsicSize.Max)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Text(text = description)
    }
}


@Preview(
    name = "TaskCompleted",
    showBackground = false,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}