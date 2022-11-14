package com.wookhyun.nonamedproject

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wookhyun.nonamedproject.data.DataSource
import com.wookhyun.nonamedproject.model.Topic
import com.wookhyun.nonamedproject.ui.theme.NoNamedProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoNamedProjectTheme {
                Scaffold(
                    content = { _ ->
                        TopicGrid(topicList = DataSource.topics)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun Screen() {
    NoNamedProjectTheme() {
        TopicGrid(topicList = DataSource.topics)
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier, topicList: List<Topic>) {
    LazyColumn() {
        items(items = topicList, itemContent = { topic: Topic ->
            if (topicList.indexOf(topic) % 2 == 0) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ImageCard(topic = topic, modifier = Modifier.weight(1f))
                    ImageCard(topic = topicList[topicList.indexOf(topic) + 1], modifier = Modifier.weight(1f))
                }
            }
        })
    }
}

@Composable
fun ImageCard(modifier: Modifier = Modifier, topic: Topic) {
    Row(modifier = modifier
        .padding(8.dp)
        .height(68.dp)
    ) {
        Image(modifier = Modifier
            .width(68.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = topic.imageResourceId),
            contentDescription = stringResource(id = topic.title))

        Column(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = topic.title),
                style = MaterialTheme.typography.body2)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_grain),
                    contentDescription = "")
                Text(modifier = Modifier.padding(start = 8.dp),
                    text = "${topic.count}",
                    style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Preview
@Composable
fun ImageCardPreview() {
    ImageCard(topic = Topic(title = R.string.photography,
        imageResourceId = R.drawable.photography,
        count = 321))
}