package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

@Composable
fun SuperHeroesApp(){
    SuperheroesTheme {
        Scaffold(topBar = {AppBar()}) {
            LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
                items(heroes) {
                    HeroItem(hero = it, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier){
    Card(elevation = 2.dp,
        modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = hero.nameRes),
                style = MaterialTheme.typography.h3)
                Text(text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1)
            }
            Spacer(Modifier.size(16.dp))
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = null,
                modifier = modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun AppBar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(56.dp),
        Arrangement.Center
    ) {
        Text(text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1)
    }
}