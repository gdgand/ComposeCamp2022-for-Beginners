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
        setContent { }
    }
}

@Composable
fun TaskCompletedScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var image = painterResource(id = R.drawable.ic_task_completed)
        Image(painter = image, contentDescription = "test")
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