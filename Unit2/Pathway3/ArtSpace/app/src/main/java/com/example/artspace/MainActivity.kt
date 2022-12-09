package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.dto.PictureItem
import com.example.artspace.ui.theme.ArtSpaceTheme

val itemList: MutableList<PictureItem> = mutableListOf()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpace(itemList)
                }
            }
        }
    }
}

// 테스트 데이터 셋팅
private fun initTestItems() {
    val item1 = PictureItem(R.drawable.image01, "1.ImageImageImageImage", "Kim", "2019")
    val item2 = PictureItem(R.drawable.image02, "2.ImageImageImageImageImageImage", "Park", "2022")
    val item3 = PictureItem(R.drawable.image03, "3.ImageImageImageImageImage", "Lee", "2011")
    val item4 = PictureItem(R.drawable.image04, "4.ImageImageImageImageImageImageImageImageImage", "Son", "2010")
    val item5 = PictureItem(R.drawable.image05, "5.ImageImageImage", "Choi", "2014")
    val item6 = PictureItem(R.drawable.image06, "6.ImageImageImageImageImageImage", "Jung", "2015")
    val item7 = PictureItem(R.drawable.image07, "7.ImageImageImageImageImageImageImageImage", "Ha", "2017")

    itemList.add(item1)
    itemList.add(item2)
    itemList.add(item3)
    itemList.add(item4)
    itemList.add(item5)
    itemList.add(item6)
    itemList.add(item7)
}

@Composable
fun ArtSpace(itemList: MutableList<PictureItem>) {
    initTestItems()

    var index = remember { mutableStateOf(0) }
    val selectItem = itemList[index.value]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        val imageModifier = Modifier.fillMaxSize(1f)
            .border(BorderStroke(2.dp, Color.DarkGray))
            .background(Color.White)
            .weight(1f)

        // 그림 이미지 표시
        Surface(
            modifier = Modifier.fillMaxSize(1f)
                .weight(1f),
            elevation = 10.dp
        ) {
            Image(
                painter = painterResource(selectItem.resId),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize(1f)
                    .border(BorderStroke(2.dp, Color.DarkGray))
                    .background(Color.White)
                    .padding(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 그림 정보 영역
        Card(
            modifier = Modifier.wrapContentWidth(),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = selectItem.imageTitle,
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.wrapContentWidth()
                )

                Row {
                    Text(
                        text = selectItem.artist,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentWidth()
                    )
                    Text(
                        text = "(${selectItem.year})",
                        color = Color.Black.copy(alpha = 0.3f),
                        fontSize = 20.sp,
                        modifier = Modifier.wrapContentWidth()
                            .padding(start = 4.dp)
                    )
                }
            }
        }

        // 버튼 영역
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 0.dp)
        ) {
            Button(
                onClick = {
                    if (index.value > 0) {
                        index.value = index.value - 1
                    }
                    else {
                        index.value = itemList.size - 1
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "이전"
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (index.value < itemList.size - 1) {
                        index.value = index.value + 1
                    }
                    else {
                        index.value = 0
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "다음"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpace(itemList)
    }
}