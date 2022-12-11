package com.example.superheroes

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesRepository
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
                    SuperHeroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperHeroesApp() {
    Scaffold(
        topBar = {
            SuperHeroesAppBar()
        }
    ) { contentPadding ->
        LazyColumn(modifier = Modifier.padding(contentPadding)) {
            items(HeroesRepository.heroes) {
                SuperHeroesCard(
                    nameRes = it.nameRes,
                    descriptionRes = it.descriptionRes,
                    imageRes = it.imageRes
                )
            }
        }
    }
}

@Composable
fun SuperHeroesCard(
    nameRes: Int,
    descriptionRes: Int,
    imageRes: Int
) {
    Card(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(id = nameRes), style = MaterialTheme.typography.h3)
                Text(stringResource(id = descriptionRes), style = MaterialTheme.typography.body1)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painterResource(id = imageRes),
                contentDescription = stringResource(id = nameRes),
                modifier = Modifier
                    .size(72.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SuperHeroesAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperHeroesTheme {
        SuperHeroesApp()
    }
}