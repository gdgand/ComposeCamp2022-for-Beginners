package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            ArticleCard(stringResource(R.string.title),  stringResource(R.string.shortDescription), stringResource(
                R.string.longDescription) )
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
//    imagePainter: Painter,
//    modifier: Modifier = Modifier,
) {
    Column() {
        val image = painterResource(R.drawable.bg_compose_background)

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)

        )

        Text(
            text = shortDescription,
            modifier = Modifier.padding(start = 16.dp, end=16.dp),
            textAlign = TextAlign.Justify
        )

        Text(
            text = longDescription,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Justify
            //이거부터 시작
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArticleCard(stringResource(R.string.title),  stringResource(R.string.shortDescription), stringResource(
            R.string.longDescription) )
}