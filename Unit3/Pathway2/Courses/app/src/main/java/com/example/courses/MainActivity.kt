package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                TopicCardList(topics = DataSource.topics)
            }
        }
    }
}

@Composable
fun TopicCardList(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(topics) { topic ->
            TopicCard(
                stringResId = topic.stringResId,
                imageResId = topic.imageResId,
                count = topic.count
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoursesTheme {
        TopicCardList(topics = DataSource.topics)
    }
}

@Preview
@Composable
fun TopicCardPreview() {
    MaterialTheme {
        TopicCard(stringResId = R.string.architecture,
            imageResId = R.drawable.architecture,
            count = 132)
    }
}

@Composable
fun TopicCard(
    @StringRes stringResId: Int,
    @DrawableRes imageResId: Int,
    count: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row() {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier.size(68.dp)
            )

            Column(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)) {
                Text(
                    text = stringResource(id = stringResId),
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.size(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = count.toString(), style = MaterialTheme.typography.caption)
                }
            }

        }
    }
}