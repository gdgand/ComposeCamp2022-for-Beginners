package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.ModifierLocalReadScope
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
    ArticleCard(
        title = stringResource(R.string.title_jetpack_compose_tutorial),
        shortDescription = stringResource(R.string.compose_short_desc),
        longDescription = stringResource(R.string.compose_long_desc),
        imagePainter = painterResource(R.drawable.bg_compose_background)
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
        Image(painter = imagePainter, contentDescription = null)

        Text(
            text = stringResource(R.string.title_jetpack_compose_tutorial),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        //첫 번째 Text 컴포저블을 24sp 글꼴 크기, 16dp 패딩(시작, 끝, 하단, 상단)으로 설정합니다.
        Text(
            text = stringResource(R.string.compose_short_desc),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        //두 번째 Text 컴포저블을 기본 글꼴 크기, 16dp 패딩(시작 및 끝), Justify 텍스트 정렬로 설정합니다.
        Text(
            text = stringResource(R.string.compose_long_desc),
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify
        )//세 번째 Text 컴포저블을 기본 글꼴 크기, 16dp 패딩(시작, 끝, 하단, 상단), Justify 텍스트 정렬로 설정합니다.
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme() {
        androidx.compose.material.Surface {
            ComposeArticleApp()
        }
    }
}