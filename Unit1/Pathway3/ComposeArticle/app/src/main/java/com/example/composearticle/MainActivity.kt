package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
    Column(modifier = Modifier.background(colorResource(R.color.background))) {
        ArticleCard(
            title = stringResource(R.string.name),
            shortDescription = stringResource(R.string.compose_short_desc),
            longDescription = stringResource(R.string.compose_long_desc),
            imagePainter = painterResource(R.drawable.android_logo))
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        )
        Text(text = title, color = Color.White)
        Text(text = shortDescription, color = colorResource(R.color.content))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleApp()
}