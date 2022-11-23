package com.compose.camp.hun.myaffirmationscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.camp.hun.myaffirmationscodelab.data.DataSource
import com.compose.camp.hun.myaffirmationscodelab.model.Affirmation
import com.compose.camp.hun.myaffirmationscodelab.ui.theme.MyAffirmationsCodelabTheme

/**
 * Card 컴포넌트
 * Card는 단일 컨테이너에 컨텐츠와 작업 영역을 표시하는 컴포넌트.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                AffirmationApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AffirmationList(affirmationList = DataSource().loadAffirmations())
    }
}

@Composable
fun AffirmationApp() {
    MyAffirmationsCodelabTheme {
        Scaffold(content = {
            AffirmationList(affirmationList = DataSource().loadAffirmations())
        })
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column() {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

/**
 * LazyColumn - android의 recyclerView와 비슷한 역할.
 * 일반 Column은 정해진 길이의 데이터를 모두 가져오는데, LazyColumn은 화면에 표시된 데이터만 가져온다.
 */
@Composable
fun AffirmationList(affirmationList: List<Affirmation>) {
    LazyColumn {
        items(affirmationList) { affirmation: Affirmation ->
            AffirmationCard(affirmation = affirmation)
        }
    }
}