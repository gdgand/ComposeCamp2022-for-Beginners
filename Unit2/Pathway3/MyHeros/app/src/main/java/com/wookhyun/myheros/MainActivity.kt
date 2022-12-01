package com.wookhyun.myheros

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wookhyun.myheros.model.HeroesRepository
import com.wookhyun.myheros.ui.theme.MyHerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHerosTheme {
                HeroesScreen()
            }
        }
    }
}

@Composable
fun HeroesScreen() {
    Scaffold(
        topBar = { TopBar() }
    ) {
        LazyColumn {
            items(HeroesRepository.heroes) { hero ->
                HereRow(hero = hero,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
            }
        }
    }
}

@Composable
fun TopBar() {
//    TopAppBar(modifier = Modifier
    Box(modifier = Modifier
        .fillMaxWidth()
        .size(56.dp)
        ) {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyHerosTheme {
        HeroesScreen()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkModeDefaultPreview() {
    MyHerosTheme {
        HeroesScreen()
    }
}