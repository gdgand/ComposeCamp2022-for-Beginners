package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Arts()
                }
            }
        }
    }
}

@Composable
fun Arts() {
    var artwork by remember { mutableStateOf(1) }
    val image_result = when(artwork) {
        1 -> painterResource(id = R.drawable.li_zhang_i_liituthey_unsplash)
        2 -> painterResource(id = R.drawable.mike_hindle_cucyxy6ck_s_unsplash)
        3 -> painterResource(id = R.drawable.jonny_gios_2whvtmbq2e8_unsplash)
        else -> painterResource(id = R.drawable.richard_deng_4vuyhuq0tlg_unsplash)
    }
    val image_desc = when(artwork) {
        1 -> stringResource(id = R.string.red_ribbon_ii)
        2 -> stringResource(id = R.string.liverpool_museum)
        3 -> stringResource(id = R.string.kissing_puffins)
        else -> stringResource(id = R.string.m_31)
    }
    val text_result = when(artwork) {
        1 -> stringResource(id = R.string.li)
        2 -> stringResource(id = R.string.mike)
        3 -> stringResource(id = R.string.jonny)
        else -> stringResource(id = R.string.richard)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image_result,
            contentDescription = image_desc,
            modifier = Modifier
                .border(BorderStroke(4.dp, Color.LightGray))
                .padding(30.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .padding(top = 10.dp, bottom = 10.dp, start = 5.dp)
        ) {
            Text(
                text = image_desc,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = text_result,
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
                Text(
                    text = "(2022)",
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if(artwork == 1) artwork = 4
                    else artwork-- },
                modifier = Modifier
                    .size(width = 100.dp, height = 35.dp)
                    .weight(1f)
                    .padding(start = 5.dp)
                ) {
                Text(text = "Previous")

            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = {
                    if(artwork == 4) artwork = 1
                    else artwork++ },
                modifier = Modifier
                    .size(width = 100.dp, height = 35.dp)
                    .weight(1f)
                    .padding(end = 5.dp)
                ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        Arts()
    }
}