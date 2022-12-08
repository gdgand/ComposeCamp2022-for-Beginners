package com.example.courses

import android.os.Bundle
import android.util.Log.w
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource



import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.Datasource
import com.example.courses.data.Topic
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
                    CoursesApp()
                }
            }
        }
    }
}


@Composable
fun TopicBox(topic: Topic, modifier: Modifier = Modifier) {

    val padding = 4.dp
    val density = LocalDensity.current

    Surface(shape = RoundedCornerShape(8.dp), color = Color.White, elevation = 8.dp
        , modifier = Modifier.fillMaxSize().height(68.dp)
            .drawWithContent {
                val paddingPx = with(density) { padding.toPx() }
                clipRect(
                    left = -paddingPx,
                    top = 0f,
                    right = size.width + paddingPx,
                    bottom = size.height + paddingPx
                ) {
                    this@drawWithContent.drawContent()
                }
            }
    ) {
        Row() {
            Image(
                painter = painterResource(topic.imageResourceId)
                , contentDescription = stringResource(topic.stringResourceId)
                , modifier = Modifier.width(68.dp)
                , contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
                Text(text = stringResource(topic.stringResourceId)
                    ,  style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    // Icon
                    Icon(painter = painterResource(R.drawable.ic_grain)
                        , contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    // numberOfClass
                    Text(text = topic.numberOfClass.toString()
                        ,  style = MaterialTheme.typography.caption
                    )
                }

            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicsGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2)
//        cells = GridCells.Adaptive(minSize = 128.dp)
//        , contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        , verticalArrangement = Arrangement.spacedBy(8.dp)
        , horizontalArrangement = Arrangement.spacedBy(8.dp)
        , modifier = modifier.padding()
    ) {
        items(topics) { topic ->
            TopicBox(topic)
        }
    }
}

@Composable
fun CoursesApp() {
    val topics = com.example.courses.data.Datasource.loadData()

    Box (modifier = Modifier.padding(8.dp)){
        TopicsGrid(topics)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoursesTheme {
        CoursesApp()
    }
}