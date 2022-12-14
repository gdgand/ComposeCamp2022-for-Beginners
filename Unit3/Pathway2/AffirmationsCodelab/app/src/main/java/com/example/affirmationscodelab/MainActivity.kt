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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationscodelab.data.Datasource
import com.example.affirmationscodelab.modal.Affirmation
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


@Composable //model에서 affirmation/ modifier기본값은 Modifier
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier=Modifier){
    Card(
        modifier = modifier.padding(8.dp), //패딩 
        elevation =4.dp //나중에 자세히
    ) {
        Column() {
            Image(
                painter = painterResource(id = affirmation.imageResourceId), 
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier= Modifier
                    .fillMaxWidth() //가로 꽉채우기
                    .height(194.dp), //높이 값 설정
                contentScale = ContentScale.Crop //이미지 크기조절 및 표시방법 결정
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId), //글가져오기
                modifier = Modifier.padding(16.dp), //패딩설정
                style = MaterialTheme.typography.h6 //텍스트 테마설정
            )
        }
    }
}

@Composable //카드들을 목록으로 만들기
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn{ //스크롤가능한 목록(모두한번에 로드하지 않음)
        items(affirmationList){
            affirmation -> AffirmationCard(affirmation)
        }
    }
}

@Preview
@Composable // 미리보기함수
private fun AffirmationCardPreview(){
    AffirmationCard(Affirmation(R.string.affirmation1,R.drawable.image1) )
}
