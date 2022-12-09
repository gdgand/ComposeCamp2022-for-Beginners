package com.example.a30daysofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofwellness.data.GameRepository
import com.example.a30daysofwellness.model.Game
import com.example.a30daysofwellness.ui.theme.A30DaysOfWellnessTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A30DaysOfWellnessTheme {
                GameListScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    A30DaysOfWellnessTheme {
        GameListScreen()
    }
}

@Composable
fun GameListScreen() {
    Scaffold(topBar = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), contentAlignment = Alignment.Center) {
            Text(text = "30 Days of Games",
                style = MaterialTheme.typography.h1
            )
        }
    }) {
        GameList()
    }
}

@Composable
fun GameList() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(GameRepository.games) { game ->
            GameItem(game)
        }
    }
}

@Composable
fun GameItem(game: Game) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            isExpanded = !isExpanded
        }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(),
        ) {
            Text(
                text = "Day ${game.dayOfMonth}",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = stringResource(id = game.titleResId))
            Image(
                painter = painterResource(id = game.imgResId),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )
            if (isExpanded) {
                Text(
                    text = stringResource(id = game.descriptionResId),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
