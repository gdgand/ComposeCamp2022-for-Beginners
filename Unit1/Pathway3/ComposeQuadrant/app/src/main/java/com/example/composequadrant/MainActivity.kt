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
            ComposeQuadrantTheme {

            }

            ComposeQuadrantApp()
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column() {
        Row(modifier = Modifier.weight(weight = 1.0f)) {
            val title1 = stringResource(R.string.first_title)
            val desc1 = stringResource(R.string.first_description)
            val modifier1 = Modifier.weight(weight = 1.0f)
            ComposableInfoCard(title1, desc1, Color.Green, modifier1)

            val title2 = stringResource(R.string.second_title)
            val desc2 = stringResource(R.string.second_description)
            val modifier2 = Modifier.weight(weight = 1.0f)
            ComposableInfoCard(title2, desc2, Color.Yellow, modifier2)
        }
        Row(modifier = Modifier.weight(weight = 1.0f)) {
            val title3 = stringResource(R.string.third_title)
            val desc3 = stringResource(R.string.third_description)
            val modifier3 = Modifier.weight(weight = 1.0f)
            ComposableInfoCard(title3, desc3, Color.Cyan, modifier3)

            val title4 = stringResource(R.string.fourth_title)
            val desc4 = stringResource(R.string.fourth_description)
            val modifier4 = Modifier.weight(weight = 1.0f)
            ComposableInfoCard(title4, desc4, Color.LightGray, modifier4)
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
            .fillMaxHeight()
            .background(color = backgroundColor)
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
            textAlign = TextAlign.Justify,
            fontSize = TextUnit.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantApp()
}