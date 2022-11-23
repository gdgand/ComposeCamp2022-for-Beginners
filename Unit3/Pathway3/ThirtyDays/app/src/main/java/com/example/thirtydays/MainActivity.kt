@file:OptIn(ExperimentalAnimationApi::class)

package com.example.thirtydays

import android.content.res.Configuration
import android.graphics.Paint
import android.media.midi.MidiDevice.MidiConnection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirtydays.model.OneDayItem
import com.example.thirtydays.model.ThirtyDaysItemList
import com.example.thirtydays.ui.theme.ThirtyDaysTheme
import com.example.thirtydays.model.*
import com.example.thirtydays.ui.theme.Green100


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
                    ThirtyDaysApp()
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysList(items: List<OneDayItem>) {

}

@Composable
fun OneDayCard(item: OneDayItem, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card(elevation = 8.dp, modifier = modifier
        .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            val dayString = "Day" + item.day.toString()
            Text(text = dayString, style = MaterialTheme.typography.h1
                , color = Color.Gray
                , modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )

            Text(text = stringResource(item.titleRes)
                , style = MaterialTheme.typography.h2
                , modifier = Modifier.padding()
            )
            Image(painter = painterResource(R.drawable.snowman)
                , contentDescription = null
                , alignment = Alignment.TopCenter
                , contentScale = ContentScale.FillWidth
                , modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(item.descriptionRes),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview("TopAppBar")
@Composable
fun ThirtyDaysTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(backgroundColor = Green100
        , modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "30 Days of Wellness"
                , style = TextStyle(fontFamily = FontFamily.Default
                    , fontSize = 24.sp
                    , fontWeight = FontWeight.Bold)
                ,
            )
        }
    }
}


//@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThirtyDaysApp() {

    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    Scaffold (topBar =  { ThirtyDaysTopAppBar() }
    ) {
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
            ),
            exit = fadeOut()
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), contentPadding = PaddingValues(12.dp)
            ) {
                itemsIndexed(ThirtyDaysItemList.items) { index, item ->

                    OneDayCard(
                        item = item
                        , modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                    )
                }
            }
        }

    }
}



@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThirtyDaysItemListPreview() {
    ThirtyDaysTheme() {
        Surface(color = MaterialTheme.colors.background
        ) {
//            var editable by remember { mutableStateOf(true) }
//            AnimatedVisibility(visible = editable) {
//                Text(text = "Edit")
//            }
//            ThirtyDaysList(items = ThirtyDaysItemList.items)
        }

    }
}


@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OneDayItemPreview() {
    val oneDayItem = OneDayItem(1, R.string.item_1, R.string.desc_1)

    ThirtyDaysTheme() {



        OneDayCard(item = oneDayItem)
    }
}

