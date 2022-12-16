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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskcompleted.ui.theme.TaskCompletedTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskCompletedScreen(imagePainter = painterResource(id = R.drawable.ic_task_completed),
                title = stringResource(id = R.string.all_task_completed) , subTitle = stringResource(id = R.string.nice_work))
        }
    }
}

@Composable
fun TaskCompletedScreen(
    imagePainter: Painter,
    title: String,
    subTitle: String,
    modifier: Modifier=Modifier,
    ) {
    Column(modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = imagePainter, contentDescription = null,
            modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically))

        Text(text = title, Modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally).padding(top = 24.dp, bottom = 8.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Text(text = subTitle, Modifier.fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally), fontSize = 16.sp)

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskCompletedScreen(imagePainter = painterResource(id = R.drawable.ic_task_completed),
        title = stringResource(id = R.string.all_task_completed) , subTitle = stringResource(id = R.string.nice_work))
}