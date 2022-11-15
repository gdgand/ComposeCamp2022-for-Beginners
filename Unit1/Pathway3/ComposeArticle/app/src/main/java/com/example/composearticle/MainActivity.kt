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
import androidx.compose.ui.layout.ContentScale
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
                ComposeArticleApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeArticlePreview() {
    ComposeArticleTheme {
        ComposeArticleApp()
    }
}

@Composable
fun ComposeArticleApp() {
    val image = painterResource(R.drawable.bg_compose_background)
    ArticleCard(
        stringResource(id = R.string.title_jetpack_compose_tutorial),
        stringResource(id = R.string.compose_short_desc),
        stringResource(id = R.string.compose_long_desc),
        imagePainter = image,
        modifier = Modifier
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
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
        )

        Text(
            text = shortDescription,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )

        Text(
            text = longDescription,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
            textAlign = TextAlign.Justify
        )
    }
}

