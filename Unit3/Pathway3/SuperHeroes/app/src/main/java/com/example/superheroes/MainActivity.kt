package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroRepository
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeroList()
                }
            }
        }
    }
}

@Composable
fun HeroList() {
    Scaffold(topBar = { TopBar() }) {
        LazyColumn(Modifier.background(MaterialTheme.colors.background)) {
            items(HeroRepository.heroes) { hero ->
                HeroItem(hero = hero)
            }
        }
    }
}

@Composable
fun HeroItem(hero: Hero) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentWidth()
            .clip(shape = MaterialTheme.shapes.medium),
        elevation = 30.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .height(72.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = hero.nameRes), style = MaterialTheme.typography.h3)
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.clip(shape = MaterialTheme.shapes.small),
                painter = painterResource(id = hero.imageRes), contentDescription = stringResource(
                    id = hero.descriptionRes
                )
            )
        }

    }
}

@Composable
fun TopBar() {
    Text(
        text = "Superheroes",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperHeroesTheme {
        HeroList()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    SuperHeroesTheme(darkTheme = true) {
        HeroList()
    }
}