package com.compose.camp.hun.superherocodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.camp.hun.superherocodelab.data.HeroesRepository
import com.compose.camp.hun.superherocodelab.model.Hero
import com.compose.camp.hun.superherocodelab.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperHeroCard(hero = HeroesRepository.heroes.first())
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
fun SuperHeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier.clip(RoundedCornerShape(16.dp))) {
        Row(modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column() {
                Text(text = stringResource(id = hero.nameRes), style = MaterialTheme.typography.h3)
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.descriptionRes),
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}