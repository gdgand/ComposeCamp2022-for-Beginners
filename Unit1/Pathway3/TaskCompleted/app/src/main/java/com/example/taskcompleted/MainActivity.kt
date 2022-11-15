package com.example.taskcompleted

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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box {
                        TaskCompletedScreen(
                            imagePainter = painterResource(id = R.drawable.ic_task_completed),
                            title = stringResource(R.string.title),
                            message = stringResource(R.string.message)
                        )
                    }

                }

            }
        }
    }
}
/*
*
* 첫 번째 Text 컴포저블을 24sp 글꼴 크기, 24dp 패딩 상단, 8dp 패딩 하단으로 설정합니다.
두 번째 Text 컴포저블을 16sp 글꼴 크기로 설정합니다.
* */
@Composable
fun TaskCompletedScreen(
    imagePainter: Painter,
    title: String,
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = message,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedTheme {
        Surface(color = MaterialTheme.colors.background) {
            Box {
                TaskCompletedScreen(
                    imagePainter = painterResource(id = R.drawable.ic_task_completed),
                    title = stringResource(R.string.title),
                    message = stringResource(R.string.message),
                )
            }

        }

    }
}