package com.compose.camp.hun.mygridbuild

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.camp.hun.mygridbuild.data.DataSource
import com.compose.camp.hun.mygridbuild.model.Topic
import com.compose.camp.hun.mygridbuild.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(DataSource.topics) { item: Topic ->
                    SingleTopicItem(topic = item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(DataSource.topics) { item: Topic ->
            SingleTopicItem(topic = item)
        }
    }
}

@Composable
fun SingleTopicItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = stringResource(id = topic.title),
                modifier = Modifier.size(68.dp)
            )
            Content(topic = topic)
        }
    }
}

@Composable
fun Content(topic: Topic) {
    Column {
        Text(
            text = stringResource(id = topic.title),
            style = Typography.body2,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_grain),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(12.dp),
            )
            Text(
                text = topic.count.toString(),
                style = Typography.caption,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}