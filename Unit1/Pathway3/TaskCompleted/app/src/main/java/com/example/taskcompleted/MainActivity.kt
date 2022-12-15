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
        setContent {
            Surface {
                TaskCompletedScreen()
            }
        }
    }
}

@Composable
fun TaskCompletedScreen() {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.task_complete_text),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.task_complement_good_text),
            fontSize = 16.sp,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedTheme {
        Surface {
            TaskCompletedScreen()
        }
    }
}