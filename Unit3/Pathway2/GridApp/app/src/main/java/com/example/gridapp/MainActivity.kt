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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.affirmationscodelab.data.DataSource
import com.example.affirmationscodelab.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopicApp()
        }
    }
}

@Composable
fun TopicApp() {
    val context = LocalContext.current

    GridAppTheme() {
        Scaffold(content={ TopicList(topicList = DataSource.topics)})
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier){
    Card(elevation = 4.dp
    ){
        Row{
            Image(painter = painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
                    .aspectRatio(1f)
            )

            Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, top=16.dp, bottom = 8.dp)) {
                Text(text = stringResource(id = topic.stringResourceId),
                    fontSize = 11.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "grain",
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(text = "${topic.count}",
                        fontSize = 9.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicList(topicList: List<Topic>,
                      modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ){
        items(topicList) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Preview
@Composable
private fun TopicCardPreview() {
    TopicCard(Topic(R.string.architecture, DataSource.topics[0].count, R.drawable.architecture))
}
