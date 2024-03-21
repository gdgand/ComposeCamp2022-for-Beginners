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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
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
                androidx.compose.material.Surface {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp() {
    val title = stringResource(id = R.string.title_jetpack_compose_tutorial)
    val shortDescription = stringResource(id = R.string.compose_short_desc)
    val longDescription = stringResource(id = R.string.compose_long_desc)
    val imagePainter = painterResource(id = R.drawable.bg_compose_background)
    Box (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        ArticleCard(title, shortDescription, longDescription, imagePainter)
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
    Column {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = shortDescription,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
        )
        Text(
            text = longDescription,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleApp()
}