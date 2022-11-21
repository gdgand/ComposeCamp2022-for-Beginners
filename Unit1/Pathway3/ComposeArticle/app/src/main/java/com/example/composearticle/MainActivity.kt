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
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme{
                Surface(color = MaterialTheme.colors.background) {
                    ArticleCard(
                        title = stringResource(R.string.title_jetpack_compose_tutorial),
                        shortDescription = stringResource(R.string.compose_short_desc),
                        longDescription = stringResource(id = R.string.compose_long_desc),
                        imagePainter = painterResource(R.drawable.bg_compose_background)
                    )

                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp() { }

@Composable
private fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column() {
        Image(painter = imagePainter, contentDescription = null,modifier.fillMaxWidth())
        Text(text = title, fontSize = 24.sp, modifier = modifier.fillMaxWidth().padding(16.dp))
        Text(text = shortDescription, textAlign = TextAlign.Justify,modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp))
        Text(text = longDescription, textAlign = TextAlign.Justify,modifier = modifier.fillMaxWidth().padding(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArticleCard(
        title = stringResource(R.string.title_jetpack_compose_tutorial),
        shortDescription = stringResource(R.string.compose_short_desc),
        longDescription = stringResource(id = R.string.compose_long_desc),
        imagePainter = painterResource(R.drawable.bg_compose_background)
    )

}