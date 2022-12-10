package com.example.superheros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheros.data.HeroesRepository
import com.example.superheros.model.Hero
import com.example.superheros.ui.theme.SuperheroesTheme
import kotlinx.coroutines.handleCoroutineException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun SuperHeroesItem(
    superHero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = 2.dp) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SuperHeroesInfo(heroNameId = superHero.nameRes, heroDescriptionId = superHero.descriptionRes)
            Spacer(modifier = Modifier.width(16.dp))
            SuperHeroesImage(superHeroImage = superHero.imageRes)
        }
    }
}

@Composable
fun SuperHeroesTopAppBar() {
    Text(
        text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
        )
}

@Composable
fun SuperHeroesApp() {
    Scaffold(topBar = {
        SuperHeroesTopAppBar()
    }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(HeroesRepository.heroes) {
                SuperHeroesItem(superHero = it)
            }
        }
    }
}

@Composable
fun SuperHeroesImage(
    superHeroImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = superHeroImage),
        contentDescription = null,

        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun SuperHeroesInfo(
    heroNameId: Int,
    heroDescriptionId: Int
) {
    Column() {
        Text(
            text = stringResource(id = heroNameId),
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = stringResource(id = heroDescriptionId),
            style = MaterialTheme.typography.body1,
            softWrap = true
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        SuperHeroesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperHeroesApp()
    }
}