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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedTheme {
                TaskCompletedScreen()
            }
        }
    }
}

@Composable
fun TaskCompletedScreen() {
    val img = painterResource(R.drawable.ic_task_completed)
    val title = "All tasks completed"
    val subTitle = "Nice work!"

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = img,
                contentDescription = null,
            )
            Text(
                text = title,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = subTitle,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedTheme {
        TaskCompletedScreen()
    }
}