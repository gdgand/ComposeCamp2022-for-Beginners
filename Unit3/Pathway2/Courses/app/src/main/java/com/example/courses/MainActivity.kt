package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoursesScreen()
                }
            }
        }
    }
}

@Composable
fun CoursesScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(DataSource.topics) { topic ->
            TopicItem(topic)
        }
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    modifier: Modifier = Modifier,
) {
    Card(elevation = 4.dp) {
        Row() {
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = null,
                modifier = modifier
                    .size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                // modifier = Modifier.padding(16.dp)
            )
            {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.body2,
                    // fontSize = 24.sp,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp),
                    )
                    Text(
                        text = topic.count.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoursesTheme {
        TopicItem(Topic(R.string.architecture, 58, R.drawable.photography))
    }
}