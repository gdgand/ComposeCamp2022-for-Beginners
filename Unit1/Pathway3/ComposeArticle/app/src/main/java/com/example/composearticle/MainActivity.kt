package com.example.composearticle

import android.graphics.Color
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
import androidx.compose.ui.text.font.FontWeight
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArticleCardWithImage(
                        stringResource(R.string.title_jetpack_compose_tutorial),
                        stringResource(R.string.compose_short_desc),
                        stringResource(R.string.compose_long_desc))
                }
            }
        }
    }
}

@Composable
fun ArticleCardWithImage(articleHeader :String, articleDesc :String, article :String){
    val articleCard =painterResource(R.drawable.bg_compose_background)
    Box {
        Column {
            Image(
                painter = articleCard,
                contentDescription = null,
                modifier = Modifier.
                fillMaxWidth())

            Text(text = articleHeader,
                modifier = Modifier.
                wrapContentWidth(Alignment.Start).
                padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Text(text = articleDesc,
                modifier = Modifier.
                wrapContentWidth(Alignment.Start).
                padding(start = 16.dp, end = 16.dp))

            Text(text = article,
                modifier = Modifier.
                wrapContentWidth(Alignment.Start).
                padding(start = 16.dp, end = 16.dp, top = 16.dp))
        }
    }

}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ArticleCardWithImage(
            stringResource(R.string.title_jetpack_compose_tutorial),
            stringResource(R.string.compose_short_desc),
            stringResource(R.string.compose_long_desc))
    }
}
