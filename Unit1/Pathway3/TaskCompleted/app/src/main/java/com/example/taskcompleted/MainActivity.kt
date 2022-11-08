package com.example.taskcompleted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedScreen(
                imagePainter = painterResource(id = R.drawable.ic_task_completed),
                title = stringResource(id = R.string.all_task_completed),
                subTitle = stringResource(id = R.string.nice_work),
            )
        }
    }
}

@Composable
fun TaskCompletedScreen(imagePainter: Painter, title: String, subTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = imagePainter, contentDescription = null)
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(text = subTitle, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedScreen(
        painterResource(id = R.drawable.ic_task_completed),
        stringResource(id = R.string.all_task_completed),
        stringResource(id = R.string.nice_work),
    )
}