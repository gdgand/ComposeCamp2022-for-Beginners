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
            Surface(color = MaterialTheme.colors.background) {
                ComposeQuadrantApp()
            }
        }
    }
}

/*
Column 내부 Row 또는 Row 내부 Column 등 표준 레이아웃 하단 레이아웃
내부에서 Modifier.weight 를 선언해주어야 한다.
외부에서 선언시 RowScopeInstance 에 접근할 수 없다고 뜬다.
 */
@Composable
fun ComposeQuadrantApp() {
    Column() {
        val modifier = Modifier.weight(1f)

        // Row 내부에 weight 값을 줌으로써 위아래 5:5 만든다
        Row(Modifier.weight(1f)) {
            // 내부 값에 weight 를 줌으로써 좌우 5:5 만든다
            ComposableInfoCard(
                title = stringResource(id = R.string.first_title),
                description = stringResource(id = R.string.first_description),
                backgroundColor = Color.Green,
                modifier = modifier
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.second_title),
                description = stringResource(id = R.string.second_description),
                backgroundColor = Color.Yellow,
                modifier = modifier
            )
        }
        // Row 내부에 weight 값을 줌으로써 위아래 5:5 만든다
        Row(Modifier.weight(1f)) {
            // 내부 값에 weight 를 줌으로써 좌우 5:5 만든다
            ComposableInfoCard(
                title = stringResource(id = R.string.third_title),
                description = stringResource(id = R.string.third_description),
                backgroundColor = Color.Cyan,
                modifier = modifier
            )
            ComposableInfoCard(
                title = stringResource(id = R.string.fourth_title),
                description = stringResource(id = R.string.fourth_description),
                backgroundColor = Color.LightGray,
                modifier = modifier
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
        // fillMaxWidth + fillMaxHeight == fillMaxSize
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
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