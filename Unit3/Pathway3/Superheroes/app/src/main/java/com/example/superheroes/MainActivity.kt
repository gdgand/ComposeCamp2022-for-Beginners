package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme {
                SuperheroApp()
            }
        }
    }
}

@Composable
fun SuperheroApp() {
    ShowSuperheroes(heroes = HeroesRepository.heroes)
}

@Composable
fun ShowSuperheroes(heroes: List<Hero>, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SuperheroesTopBar()
        }
    ) {

        LazyColumn(
            modifier = modifier
                .background(MaterialTheme.colors.background)
        ) {
            items(heroes) {
                ShowSuperhero(it)
            }
        }
    }
}


@Composable
fun ShowSuperhero(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .sizeIn(minHeight = 72.dp)
                .clip(MaterialTheme.shapes.large)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SuperheroInfo(
                heroName = hero.nameRes,
                heroDescription = hero.descriptionRes,
                Modifier.weight(1f)
            )
            SuperheroIcon(heroImage = hero.imageRes)
        }
    }
}

@Composable
fun SuperheroInfo(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun SuperheroIcon(
    @DrawableRes heroImage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))

    ) {
        Image(
            painter = painterResource(heroImage),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun SuperheroesTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview
@Composable
fun SuperheroAppPreview() {
    SuperheroesTheme(darkTheme = false) {
        SuperheroApp()
    }
}

@Preview
@Composable
fun SuperheroAppDarkPreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroApp()
    }
}