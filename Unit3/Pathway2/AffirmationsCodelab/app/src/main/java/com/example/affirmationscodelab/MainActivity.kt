/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmationscodelab
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationscodelab.data.Datasource
import com.example.affirmationscodelab.model.Affirmation
import com.example.affirmationscodelab.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationsTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}

/**
 * 아이템들을 리스트로 보여주기 위한 메소드
 * @param affirmationList: 보여줄 데이터 리스트
 */
@Composable
private fun AffirmationList(
    // 리스트 객체를 넘겨받음
    affirmationList: List<Affirmation>
) {
    // Column 과 다르게 고정되지 않은 항목을 로드할 때 유리
    LazyColumn {
        // list Item 추가
        items(affirmationList) { affirmation ->
            // Item 내부 접근하여 실행
            AffirmationCard(affirmation = affirmation)
        }
    }
}

/**
 * 이미지와 텍스트를 보여줄 뷰 세팅
 * @param affirmation: 이미지와 텍스트를 가지고 있는 데이터 타입
 */
@Composable
fun AffirmationCard(
    affirmation: Affirmation
) {
    // 머터리얼 테마 카드 뷰
    Card(
        // 패딩 값 8, 그림자 세팅
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        // 이미지와 텍스트를 상,하로 세팅
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                // 가로 모두 차지
                modifier = Modifier
                    .fillMaxWidth(),
                // 비율을 균등하게 조절
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                // 이미 정의된 텍스트 스타일 가져오기
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationApp()
}
