package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            ComposeArticleApp()
        }
    }
}

@Composable
fun ComposeArticleApp() {
    ComposeArticleTheme {
        ArticleCard(
            stringResource(R.string.title_jetpack_compose_tutorial),
            stringResource(R.string.compose_short_desc),
            stringResource(R.string.compose_long_desc),
            painterResource(R.drawable.bg_compose_background),
        )
    }
}

@Composable
private fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Image(
            painter = imagePainter,
            contentDescription = title,
            modifier = Modifier.fillMaxWidth()
        )
        Text(title, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Text(
            shortDescription,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Text(
            longDescription,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ArticleCard(
            stringResource(R.string.title_jetpack_compose_tutorial),
            stringResource(R.string.compose_short_desc),
            stringResource(R.string.compose_long_desc),
            painterResource(R.drawable.bg_compose_background),
        )
    }
}