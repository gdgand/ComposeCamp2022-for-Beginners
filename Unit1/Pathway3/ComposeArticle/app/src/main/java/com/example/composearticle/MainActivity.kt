package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp() {
    ArticleCard(
        title = stringResource(R.string.title_jetpack_compose_tutorial),
        shortDescription = stringResource(R.string.compose_short_desc),
        longDescription = stringResource(R.string.compose_long_desc),
        imagePainter = painterResource(R.drawable.bg_compose_background),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    )
}

@Composable
private fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column() {
        Image(
            painter = imagePainter,
            contentDescription = null
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier
        )
        Text(
            text = shortDescription,
            fontSize = 16.sp,
            modifier = modifier
        )
        Text(
            text = longDescription,
            fontSize = 16.sp,
            modifier = modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ArticleCard(
            title = stringResource(R.string.title_jetpack_compose_tutorial),
            shortDescription = stringResource(R.string.compose_short_desc),
            longDescription = stringResource(R.string.compose_long_desc),
            imagePainter = painterResource(R.drawable.bg_compose_background),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
        )
    }
}