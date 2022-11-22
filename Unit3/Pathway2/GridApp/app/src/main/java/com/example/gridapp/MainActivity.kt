package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
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

                    TopicGrid(DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun TopicGrid(topicList: List<Topic>, modifier: Modifier = Modifier) {
    val rowsCount = 1 + (topicList.size - 1) / 2

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val maxWidth = this.maxWidth

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(all = 8.dp)
        ){
            items(rowsCount) { rowIndex ->
                val rangeStart = rowIndex * 2
                var rangeEnd = rangeStart + 1
                if (rangeEnd > topicList.lastIndex) rangeEnd = topicList.lastIndex

                TopicRowOfGrid(
                    topicList.slice(rangeStart..rangeEnd),
                    (maxWidth - 24.dp) / 2
                )
            }
        }
    }
}

@Composable
fun TopicRowOfGrid(topicList: List<Topic>, columnWidth: Dp) {


    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ){
        items(topicList) { topic -> TopicCard(topic, Modifier.width(columnWidth)) }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row() {
            Image(
                painter = painterResource(id = topic.imageResourceId), 
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .size(68.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    Text(
                        text = "#",
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${topic.caption}",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TopicCard(Topic(R.string.fashion, 12, R.drawable.fashion))
}