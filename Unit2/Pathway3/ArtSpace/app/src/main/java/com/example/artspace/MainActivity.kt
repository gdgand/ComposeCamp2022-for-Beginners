package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var index by remember { mutableStateOf(0) }
    val pictures: Array<Pictures> = Pictures.values()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        ImageBox(drawableRes = pictures[index].drawableRes)
        titleBox(title = pictures[index].title, author = pictures[index].author)
        ButtonBox(
            onPreviousClick = {
                if (--index < 0) index = pictures.lastIndex
            },
            onNextClick = {
                if (++index > pictures.lastIndex) index = 0
            }
        )
    }
}

@Composable
fun ImageBox(@DrawableRes drawableRes: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .border(2.dp, Color.Gray)
            .padding(20.dp)
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null
        )
    }
}

@Composable
fun titleBox(title: String, author: String, modifier: Modifier = Modifier) {
    Surface(
        elevation = 10.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(text = title, fontSize = 20.sp)
            Text(text = author, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ButtonBox(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
    ) {
        Button(modifier = Modifier.width(100.dp),
            onClick = { onPreviousClick.invoke() }
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(modifier = Modifier.width(100.dp),
            onClick = { onNextClick.invoke() }
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}