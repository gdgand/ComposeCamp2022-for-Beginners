

package com.example.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydays.ui.theme.ThirtyDaysTheme
import com.example.thirtydays.ui.theme.model.Tip
import com.example.thirtydays.ui.theme.model.TipDatasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThirthDaysScreen()
                }
            }
        }
    }
}

@Composable
fun ThirthDaysScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) {
        TipList(Tips = TipDatasource.Tips)
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp)
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            // color = MaterialTheme.colors.primary,
        )
    }
}


@Composable
fun TipList(
    Tips: List<Tip>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        itemsIndexed(Tips) { index, tip ->
            TipItem(
                tip = tip,
                index = index,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TipItem(
    tip: Tip,
    index: Int,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp),
        onClick = { expanded = !expanded },
    ) {
        Column(
            // modifier = Modifier.fillMaxWidth()
            modifier = Modifier
                //.padding(16.dp, 8.dp)
                .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Text(
                text = "Day $index",
                style = MaterialTheme.typography.h1,
                color = Color(0xFF838383),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Text(
                text = stringResource(
                    id = tip.titleRes,
                    index
                ),
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Image(
                painter = painterResource(id = tip.imageRes),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(140.dp)
                    .align(CenterHorizontally)
            )
            if(expanded) {
                Text(
                    text = stringResource(
                        id = tip.contentRes,
                        index
                    ),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirtyDaysTheme {
        // TipItem(Tip(R.string.title, R.drawable.dice_1, R.string.content), 1)
        //TipList(Tips = TipDatasource.Tips)
        ThirthDaysScreen()
    }
}