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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // Surface 컨테이너 - MaterialTheme 배경색을 가져와서 사용하겠다
                Surface(color = MaterialTheme.colors.background) {
                    BirthdayGreetingWithImage(
                        message = stringResource(id = R.string.happy_birthday_text),
                        from = stringResource(id = R.string.signature_text))
                }
            }
        }
    }
}

// 이미지 형식 지정
@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    // 이미지를 id 값으로 가져오기
    val image = painterResource(id = R.drawable.androidparty)

    Box {
        Image(
            // 그려야할 이미지를 상단에서 id 값을 통하여 가져온 이미지로 등록
            painter = image,
            // TalkBack(안드로이드 스크린리더, 읽어주기) 를 생각
            // 의미없는 배경화면인데 읽어준다면 혼란을 주기 쉽기 때문에 null 할당
            contentDescription = null,
            // 전체화면 대응
            modifier = Modifier
                // 세로
                .fillMaxHeight()
                // 가로
                .fillMaxWidth(),
            // 비율을 유지한 상태로 이미지 키우기
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = message, from = from)
    }
}

// 텍스트 형식 지정
@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
    // 텍스트가 겹치는 것을 방지하기 위하여 세로로 겹치지 않게 지정, Row(가로)
    Column {
        // Compose Text
        Text(
            text = message,
            // 글자 크기 지정
            fontSize = 36.sp,
            // 글자 속성 지정
            modifier = Modifier
                // 가로 전체 뷰를 사용하겠다고 지정
                .fillMaxWidth()
                // 텍스트 좌,우 배치 설정
                .wrapContentWidth(Alignment.Start)
                // 패딩 값 지정(내부 빈공간)
                .padding(start = 16.dp, top = 16.dp)
        )
        // 상단 주석 참고
        Text(
            text = from,
            // 글자 크기 지정
            fontSize = 24.sp,
            modifier = Modifier
                // 가로 전체 뷰를 사용하겠다고 지정
                .fillMaxWidth()
                // 우측 정렬
                .wrapContentWidth(Alignment.End)
                // 패딩 지정
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

// 미리보기(배경색 추가, 미리보기 이름 지정
@Preview(showBackground = true, name = "미리보기!")
// 컴파일러에게 컴포즈 사용한다고 알림
@Composable
// 미리보기 함수
private fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        BirthdayGreetingWithImage(
            message = stringResource(id = R.string.happy_birthday_text),
            from = stringResource(id = R.string.signature_text)
        )
    }
}