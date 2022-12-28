package com.example.coursesapp

import android.os.Bundle
import android.view.accessibility.CaptioningManager.CaptionStyle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.data.DataSource
import com.example.coursesapp.model.Topic
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    ShowGridCoursesInfo(coursesList = DataSource.topics)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowGridCoursesInfo(coursesList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 140.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(coursesList) { course ->
            CoursesCard(course)
        }
    }
}

@Composable
fun CoursesCard(courseCard: Topic, modifier: Modifier = Modifier) {
    Card(elevation = 4.dp) {
        Row {
            Image(
                painter = painterResource(courseCard.topicImage),
                contentDescription = stringResource(courseCard.topicTitle),
                modifier = Modifier
                    .size(68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(courseCard.topicTitle),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.body2,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = courseCard.courseCountString,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CoursesAppPreview() {
    CoursesAppTheme {
        CoursesApp()
    }
}