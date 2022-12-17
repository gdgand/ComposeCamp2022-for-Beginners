package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
            ComposeArticleTheme {
                // 백그라운드 컬러를 변경할 때 surface로 감싸준다.
                Surface(color = MaterialTheme.colors.background) {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeArticlePreview() {
    ComposeArticleTheme {
        // 여기는 안감싸줘도 될듯?
        Surface() {
            ComposeArticleApp()
        }
    }
}

@Composable
fun ComposeArticleApp() {
    val image = painterResource(R.drawable.bg_compose_background)
    ArticleCard(
        stringResource(id = R.string.title_jetpack_compose_tutorial),
        stringResource(id = R.string.compose_short_desc),
        stringResource(id = R.string.compose_long_desc),
        imagePainter = image
        // 생성자에서 초기화를 해주기 때문에 생략
//        modifier = Modifier
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
    // Column 생성자에 modifier 파라미터를 넣어준다.
    Column(
        modifier = modifier
        // 화면크기는 Column 파라미터에 맞지 않는 값이다.
//            .fillMaxWidth()
//            .fillMaxHeight()
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            // 이미지를 화면가로 사이즈 맞출 필요가 없나?, match_parent 와 동일?
//            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
            // fillMaxWidth, wrapContentWidth 속성은 텍스트를 정렬해야 될 때 사용
            // ex) Alignment.CenterHorizontally
//                .fillMaxWidth()
//                .wrapContentWidth(Alignment.Start)
        )

        Text(
            text = shortDescription,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
//                .fillMaxWidth()
//                .wrapContentWidth()
            textAlign = TextAlign.Justify
        )

        Text(
            text = longDescription,
            modifier = Modifier.padding(16.dp),
//                .fillMaxWidth()
//                .wrapContentWidth()
            textAlign = TextAlign.Justify
        )
    }
}

