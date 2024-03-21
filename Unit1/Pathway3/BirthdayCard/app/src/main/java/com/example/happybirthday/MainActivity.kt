/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background) {
                    BirthdayGreetingWithImage(getString(R.string.birthday_text_message), getString(R.string.signature_text))
                }
            }
        }
    }
}

// 7. 텍스트 정렬 및 패딩 추가
@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
    // Create a column so that texts don't overlap
//    Column {
//        Text("$message from $from" , fontSize =  36.sp)
//        Text("Happy Birthday from $from" )
//    }


    Column {
        Column {
            Text(message//.reapeat(1)
                , fontSize =  36.sp
                , color = Color.Blue
                , fontFamily = FontFamily.Serif
//                , textAlign = TextAlign.Center
                , modifier = Modifier
//                    .width(600.dp)
//                    .background(color = Color.Green)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(
                        start = 16.dp,
                        top = 16.dp
                    )
            )

            Text(from//.repeat(1)
                , fontSize =  26.sp
//                , color = Color.Blue
                , fontFamily = FontFamily.SansSerif
                , textAlign = TextAlign.End
                , modifier = Modifier
//                    .width(600.dp)
//                    .background(color = Color.Green)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(
                        top = 4.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            )
        }
        Box {
            Row { Text(" ") }
//            val offset = Offset(5.0f, 10.0f)
//            Text("Box", fontSize = 24.sp
//                , fontWeight = FontWeight.Bold
//                , textAlign = TextAlign.Center
//                , modifier = Modifier.width(300.dp)
//                , fontFamily = FontFamily.Serif
//                , shadow = Shadow( color = Color.Blue
//                        , offset = offset
//                        , blurRadius = 3f
//                          )
//            )
        }
    }

}

private fun ColumnScope.Row(modifier: Alignment.Vertical, any: Any?) {

}

// 5. Box 레이아웃 추
@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    val image = painterResource(R.drawable.androidparty)
    Box() {
        Image(
            painter = image
            , contentDescription = null
             ,modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
            , contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = message, from = from)
    }

}



// 4. 이미지 컴포저블 추가
@Preview(showBackground = true,
    name = "MyBirthdayCard",
    showSystemUi = true)
@Composable
private fun BirthdayCardPreview() {
//    BirthdayGreetingWithText("Hello", "me!!")
    BirthdayGreetingWithImage("Happy Birthday! Sam", "from me!!")
}

