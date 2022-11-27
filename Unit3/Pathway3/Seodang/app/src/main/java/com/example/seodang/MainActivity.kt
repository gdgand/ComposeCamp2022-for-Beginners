package com.example.seodang

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seodang.model.Quote
import com.example.seodang.model.quotes
import com.example.seodang.ui.theme.SeodangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeodangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    QuoteItem(quote = quotes[0])
                    SeodangApp()
                }
            }
        }
    }
}

@Composable
fun SeodangApp(){
    Scaffold(
        topBar = {
            SeodangTopAppBar()
        }
    ){
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ){
            items(quotes){
                QuoteItem(quote = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuoteItem(quote: Quote, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp,
        onClick = { expanded = !expanded }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 50.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
        ){
            Row(
                modifier = Modifier.padding(
                    top=8.dp,
                    bottom=4.dp
                )
            ) {
                Text(
                    text = quote.day,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(
                        end=4.dp
                    )
                )
                Text(
                    text = stringResource(id = quote.source),
                    style = MaterialTheme.typography.h3,
                )
            }

            Text(
                text = quote.quote,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(
                    bottom=4.dp
                )
            )
            Text(
                text = stringResource(id = quote.pronounce),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(
                    bottom=4.dp
                )
            )
            if(expanded) {
                Text(
                    text = stringResource(id = quote.meaning),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
private fun SeodangTopAppBar(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 56.dp)
            .padding(
                top = 16.dp,
                bottom = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "七日書堂",
            style = MaterialTheme.typography.h1,
        )
        Text(
            text = stringResource(id = R.string.app_desc),
            style = MaterialTheme.typography.h3,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SeodangTheme(darkTheme = false) {
//        QuoteItem(quote = quotes[0])
        SeodangApp()
    }
}