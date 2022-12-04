package com.wookhyun.myheros

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wookhyun.myheros.model.Hero
import com.wookhyun.myheros.model.HeroesRepository
import com.wookhyun.myheros.ui.theme.Cabin
import com.wookhyun.myheros.ui.theme.MyHerosTheme

@Composable
fun HereRow(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp), elevation = 2.dp) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(74.dp),
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)) {
                Text(text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.h3)
                Text(text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1)
            }
            Box(modifier = Modifier
                .size(72.dp)
                .clip(shape = MaterialTheme.shapes.medium)) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}


@Preview
@Composable
fun RowPreview() {
    MyHerosTheme() {
        HereRow(hero = HeroesRepository.heroes[3])
    }
}
