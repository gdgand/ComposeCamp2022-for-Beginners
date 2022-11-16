package com.example.taskcompleted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedTheme() {
//                ComposeHelper()
//                TaskCompletedScreen()
                ComposeQuadrant()
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    Row(Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {
            ComposeCard(
                "Text composable",
                "Displays text and follows Material Design guidelines.",
                Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                "Row composable",
                "A layout composable that places its children in a horizontal sequence.",
                Color.Cyan,
                modifier = Modifier.weight(1f)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            ComposeCard(
                "Image composable",
                "Creates a composable that lays out and draws a given Painter class object.",
                Color.Yellow,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                "Column composable",
                "A layout composable that places its children in a vertical sequence.",
                Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeCard(
    title: String,
    content: String,
    backgroundColor: Color,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TaskCompletedScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StateIcon()
        Text(
            text = stringResource(R.string.text_task_state),
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.text_notification),
            fontSize = 16.sp
        )
    }
}

@Composable
fun StateIcon() {
    val image = painterResource(id = R.drawable.ic_task_completed)

    Image(
        painter =
        image,
        contentDescription = "taskStateImage"
    )
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
