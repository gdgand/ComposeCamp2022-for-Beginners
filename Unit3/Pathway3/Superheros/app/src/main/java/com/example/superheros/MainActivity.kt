package com.example.superheros

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheros.model.Hero
import com.example.superheros.model.HeroesRepository
import com.example.superheros.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperherosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperherosApp()
                }
            }
        }
    }
}


@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier= modifier
            , shape = Shapes.medium
            , elevation = 2.dp) {
        Row(modifier = Modifier.fillMaxSize().padding(16.dp).sizeIn(minHeight = 72.dp)

        ) {
            Column(modifier = Modifier.weight(1f)
//                .fillMaxHeight()
//                , verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(hero.nameRes), style = MaterialTheme.typography.h3
                    , modifier = Modifier
                )
                Text(text = stringResource(hero.descriptionRes), style = MaterialTheme.typography.body1
                    , modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.width(16.dp))


            Box( modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp))) {
                Image(painter = painterResource(hero.imageRes), contentDescription = null
                    , alignment = Alignment.TopCenter
                    , contentScale = FillWidth
                )
            }

//            Card(shape = Shapes.small
//                , modifier = Modifier.fillMaxHeight()
//            ) {
//                Image(painter = painterResource(hero.imageRes), contentDescription = null
//                    , contentScale = ContentScale.Fit
//                )
//            }

        }
    }
}

@Composable
fun SuperherosApp() {
    Scaffold(topBar = {
        Box(modifier = Modifier.fillMaxWidth().size(52.dp)
            , contentAlignment = Alignment.Center
        ) {

            Text(text = stringResource(R.string.app_name)
                , style = MaterialTheme.typography.h1)
        }

//        Column(modifier = Modifier.height(56.dp).fillMaxSize()
//             .background(color = MaterialTheme.colors.background)
//            , horizontalAlignment = Alignment.CenterHorizontally
//            , verticalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Text(text = stringResource(R.string.app_name)
//                , style = MaterialTheme.typography.h1)
//        }
    }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)
//            , contentPadding = PaddingValues( = 8.dp)
        , verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            items(items = HeroesRepository.heroes) {
                HeroItem(hero = it)
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun DefaultPreview() {
    SuperherosTheme(darkTheme = true) {
        SuperherosApp()
    }
}
