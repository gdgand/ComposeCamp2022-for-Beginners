package com.example.gridbuild

import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.gridbuild.data.Datasource
import com.example.gridbuild.model.Topic
import com.example.gridbuild.ui.theme.GridBuildTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridBuildTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                ) {
                    GridWithTwoColumns(Datasource.topics)
                }
            }
        }
    }
}

@Composable
fun TopicItemLayout(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(elevation = 4.dp) {
        Row() {
            Image(
                painter = painterResource(id = topic.drawableResId),
                contentDescription = stringResource(id = topic.stringResId),
                modifier = Modifier
                    .size(68.dp)
            )
            Column() {
                Text(
                    text = stringResource(id = topic.stringResId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(16.dp,16.dp,16.dp,8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = stringResource(id = R.string.grainImageContentDescription),
                        modifier = Modifier
                            .padding(16.dp, 0.dp, 8.dp, 0.dp)
                            .size(16.dp),
                        colorFilter = ColorFilter.lighting(Color.Black, Color.Black),
                    )
                    Text(
                        text = topic.captionValue.toString(),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}


@Composable
fun GridWithTwoColumns(topics: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    )
    {
        items(topics) {
            topic ->
            TopicItemLayout(topic = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridBuildTheme {
        TopicItemLayout(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}