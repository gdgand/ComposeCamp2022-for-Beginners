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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TaskCompletedScreen() }
    }
}

@Composable
fun TaskCompletedScreen() {
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Text(
            text = stringResource(R.string.all_task_completed),
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
                .wrapContentSize(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedTheme {
        Surface {
            TaskCompletedScreen()
        }
    }
}

// 도움된 내용
// https://velog.io/@blucky8649/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Jetpack-Compose-%EC%B0%8D%EB%A8%B9-%ED%95%B4%EB%B3%B4%EA%B8%B0-2%ED%8E%B8-%ED%85%8D%EC%8A%A4%ED%8A%B8-%EC%B6%9C%EB%A0%A5