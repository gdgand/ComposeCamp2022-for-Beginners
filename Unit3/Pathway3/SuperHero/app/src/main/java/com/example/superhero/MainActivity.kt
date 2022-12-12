package com.example.superhero

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
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
import com.example.superhero.data.HeroRepository
import com.example.superhero.model.Hero
import com.example.superhero.ui.theme.SuperHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    SuperHeroApp()
                }
            }
        }
    }
}

@Composable
fun SuperHeroApp() {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = { TopBar() }
    ) {
        LazyColumn {
            items(HeroRepository.heroes) { hero ->
                HeroCard(hero)
            }
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .sizeIn(minHeight = 72.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .clip(CircleShape.copy(CornerSize(16.dp)))
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NameAndDescription(hero, Modifier.weight(1f))
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(72.dp)
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = null,
                    // 이미지 크기를 가로 길이에 맞춰 비율 유지하며 크기 조정
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Composable
fun NameAndDescription(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = hero.nameRes),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(id = hero.descriptionRes),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_title),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CardPreview() {
    SuperHeroTheme {
        HeroCard(hero = HeroRepository.heroes[0])
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperHeroTheme {
        SuperHeroApp()
    }
}
