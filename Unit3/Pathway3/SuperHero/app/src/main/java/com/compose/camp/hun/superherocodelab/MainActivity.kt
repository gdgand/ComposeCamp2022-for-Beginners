package com.compose.camp.hun.superherocodelab

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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.camp.hun.superherocodelab.data.HeroesRepository
import com.compose.camp.hun.superherocodelab.model.Hero
import com.compose.camp.hun.superherocodelab.ui.theme.Shapes
import com.compose.camp.hun.superherocodelab.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme() {
                Scaffold(topBar = { SuperHeroAppBar() }) {
                    LazyColumn() {
                        items(HeroesRepository.heroes) { hero: Hero ->
                            SuperHeroCard(hero = hero)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme() {
        SuperHeroCard(hero = HeroesRepository.heroes.first())
    }
}

@Composable
fun SuperHeroAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h1)
    }
}

@Composable
fun SuperHeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(), elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SuperHeroInformation(name = hero.nameRes, description = hero.descriptionRes)
            Spacer(modifier = Modifier.size(16.dp))
            SuperHeroIcon(heroIcon = hero.imageRes, contentDescription = hero.descriptionRes)
        }
    }
}

@Composable
fun SuperHeroInformation(
    @StringRes name: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = name), style = MaterialTheme.typography.h3)
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.body1,
            maxLines = 2
        )
    }
}

@Composable
fun SuperHeroIcon(
    @DrawableRes heroIcon: Int,
    @StringRes contentDescription: Int?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = heroIcon),
        contentDescription = contentDescription?.let { stringResource(id = it) },
        modifier = modifier
            .clip(Shapes.small),
        alignment = Alignment.CenterEnd
    )
}