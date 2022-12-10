package com.example.composearticle

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview()
                }
            }
        }
    }
}



@Composable
fun ImageHeader() {
    val image = painterResource(id = R.drawable.bg_compose_background)

    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.titlete_text),
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
fun FirstParagraph() {
    Text(
        text = stringResource(id = R.string.first_paragraph_text),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        textAlign =  TextAlign.Justify
    )
}

@Composable
fun SecondParagraph() {
    Text(
        text = stringResource(id = R.string.second_paragraph_text),
        modifier = Modifier.padding(16.dp),
        textAlign =  TextAlign.Justify
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column {
        ImageHeader()
        Title()
        FirstParagraph()
        SecondParagraph()
    }
}