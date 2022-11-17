package com.example.taskcompleted

import android.os.Bundle
import android.os.Message
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
            TaskCompletedTheme {
                Surface {
                    TaskCompletedPreview()
                }
            }
        }
    }
}

@Composable
fun TaskCompletedWithTextAndImage(modifier: Modifier = Modifier, text1: String, text2 : String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        val image = painterResource(R.drawable.ic_task_completed)
        Image(
            painter = image,
            contentDescription = null,
        )
        
        Text(
            text = text1,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 24.dp, end = 8.dp)
        )
        Text(
            text = text2,
            fontSize = 16.sp,
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

    }
}



@Preview(showBackground = false)
@Composable
fun TaskCompletedPreview() {
    TaskCompletedWithTextAndImage(
        modifier = Modifier.fillMaxSize()
        .wrapContentSize(Alignment.Center),
        text1 = "All tasks completed", text2 = "Nice work!",)
}