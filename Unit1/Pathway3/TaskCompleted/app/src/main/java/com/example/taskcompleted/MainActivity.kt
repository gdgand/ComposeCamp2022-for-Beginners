package com.example.taskcompleted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedScreen(
                image = painterResource(R.drawable.ic_task_completed),
                result = stringResource(R.string.all_task_completed),
                message = stringResource(R.string.nice_work),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TaskCompletedScreen(
    image: Painter,
    result: String,
    message: String,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            contentScale = ContentScale.Fit
        )
        Text(
            text = result,
            fontSize = 24.sp,
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(0.dp, 24.dp, 0.dp, 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = message,
            fontSize = 16.sp,
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedScreen(
        image = painterResource(R.drawable.ic_task_completed),
        result = stringResource(R.string.all_task_completed),
        message = stringResource(R.string.nice_work),
        modifier = Modifier
            .wrapContentHeight(CenterVertically)
            .wrapContentWidth(CenterHorizontally)
    )
}