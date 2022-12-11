package com.example.thirydaysofdogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirydaysofdogs.ui.theme.Green100
import com.example.thirydaysofdogs.ui.theme.ThiryDaysOfDogsTheme
import com.example.woofcodelab.data.Information
import com.example.woofcodelab.data.infos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThiryDaysOfDogsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThiryDaysOfDogsApp()
                }
            }
        }
    }
}

@Composable
fun ThiryDaysOfDogsApp() {
    Scaffold(topBar = {TopAppBar()}) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(infos) {
                InfoItem(info = it)
            }
        }
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = MaterialTheme.colors.primary)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun InfoItem(info: Information, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (expanded) Green100 else MaterialTheme.colors.surface,
    )

    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(),
        elevation = 4.dp
    )
    {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            )
                .background(color = color)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)) {
                Text(
                    text = "Day ${info.day}",
                    modifier = modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = stringResource(info.title),
                    style = MaterialTheme.typography.h2
                )
            }

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clickable { expanded = !expanded },
                contentScale = ContentScale.Crop,
                painter = painterResource(info.imageResourceId),
                contentDescription = null
            )

            if(expanded) {
                InfoDetail(infoDetail = info.detail)
            }
        }
    }
}

@Composable
fun InfoDetail(@StringRes infoDetail: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(infoDetail),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThiryDaysOfDogsTheme {
    }
}