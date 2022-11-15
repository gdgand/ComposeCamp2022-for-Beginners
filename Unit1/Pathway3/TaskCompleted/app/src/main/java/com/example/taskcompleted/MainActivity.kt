package com.example.taskcompleted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedTheme() {
                ComposeHelper()
            }
        }
    }
}

@Composable
fun TaskCompletedScreen() {
}

@Composable
fun ComposeHelper() {
    Column {
        BannerImage()
        TitleText(text = stringResource(R.string.text_title))
        MainText(
            text = stringResource(id = R.string.text_introduce),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        MainText(text = stringResource(id = R.string.text_content))
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        fontSize = 24.sp,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun MainText(
    text: String,
    modifier: Modifier = Modifier
        .padding(16.dp)
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun BannerImage() {
    val image = painterResource(R.drawable.bg_compose_background)
    Box {
        Image(
            painter = image,
            contentDescription = "bannerImage",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
