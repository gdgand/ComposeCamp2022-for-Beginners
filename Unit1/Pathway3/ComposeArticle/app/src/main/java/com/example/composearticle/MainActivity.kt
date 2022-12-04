package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
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

@Composable
fun ComposeArticleApp() {
    val title = "Jetpack Compose tutorial"
    val shortDescription = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs."
    val longDescription = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."
    val img = painterResource(id = R.drawable.bg_compose_background)

    ArticleCard(
        title = title,
        shortDescription = shortDescription,
        longDescription = longDescription,
        imagePainter = img
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
            modifier = modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = longDescription,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ComposeArticleApp()
    }
}