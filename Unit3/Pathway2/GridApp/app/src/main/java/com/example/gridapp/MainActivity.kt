package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridapp.data.DataSource
import com.example.gridapp.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CourseList(courseList = DataSource.topics)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseList(courseList: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(courseList) { topic ->
            Course(topic)
        }
    }
}

@Composable
fun Course(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Row() {
            Image(
                painter = painterResource(topic.imageId),
                contentDescription = stringResource(topic.titleId),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
            )
            Column() {
                Text(
                    text = stringResource(topic.titleId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)
                )
                Row(){
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = topic.numOfCourse.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridAppTheme {
        Course(Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}