package com.example.coursesapp

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CoursesApp()
                }
            }
        }
    }
}

// 시험적인 API 를 사용
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoursesApp() {
    // grid 형식
    LazyVerticalGrid(
        // 2개의 아이템을 한줄에 보여주며 아이템간 간격 8dp
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // 패딩값 지정
        modifier = Modifier.padding(8.dp)
    ) {
        // 보여질 리스트 아이템을 넘겨주고 개별 데이터를 꺼내서 세팅
        items(DataSource.topics) { topic ->
            CoursesCard(topic)
        }
    }
}

@Composable
fun CoursesCard(
    topic: Topic
) {
    // 카드 뷰로 감싸고 그림자 효과
    Card(elevation = 4.dp) {
        Row {
            Image(
                painter = painterResource(id = topic.painterResource),
                contentDescription = null,
                modifier = Modifier
                    // 이미지 사이즈 68 dp
                    .size(68.dp)
                    // 내부 조건에 맞게 크기 조정 
                    .aspectRatio(1f)
            )
            Column(
                // 패딩 지정
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                // 세로 자식 뷰 사이 간격 8 dp
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = topic.stringResource),
                    style = MaterialTheme.typography.body2
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    // 가로 자식 뷰 사이 간격 8 dp
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = topic.caption.toString(),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "58",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesPreview() {
    CoursesAppTheme {
        CoursesApp()
    }
}
