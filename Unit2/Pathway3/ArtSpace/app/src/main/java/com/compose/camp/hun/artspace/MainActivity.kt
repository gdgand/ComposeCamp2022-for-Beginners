package com.compose.camp.hun.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import com.compose.camp.hun.artspace.model.artList
import com.compose.camp.hun.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            color = MaterialTheme.colors.background
        ) {
            ArtSpace()
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var artIndex by remember { mutableStateOf(0) }

    val art = artList[artIndex]

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        ImageSection(drawableRes = art.image)
        ArtDescription(art.title, art.desc, "(${art.year})", modifier)
        BottomSection(
            prevOnClick = { artIndex = if (artIndex == 0) artList.lastIndex else artIndex - 1 },
            nextOnClick = { artIndex = if (artIndex == artList.lastIndex) 0 else artIndex + 1 }
        )
    }
}

@Composable
fun ImageSection(
    @DrawableRes drawableRes: Int,
    contentDescription: String? = null,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        elevation = 8.dp,
        border = BorderStroke(width = 1.5.dp, color = Color.DarkGray),
    ) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = contentDescription,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ArtDescription(title: String, desc: String, year: String, modifier: Modifier = Modifier) {
    Surface(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = title, fontSize = 32.sp)
            Row {
                Text(text = "$desc ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = year)
            }
        }
    }
}

@Composable
fun BottomSection(modifier: Modifier = Modifier, prevOnClick: () -> Unit, nextOnClick: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        BottomButton(
            onClick = prevOnClick,
            buttonText = stringResource(id = R.string.previous),
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
        )
        BottomButton(
            onClick = nextOnClick,
            buttonText = stringResource(id = R.string.next),
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun BottomButton(
    modifier: Modifier = Modifier, onClick: () -> Unit, buttonText: String,
) {
    Button(onClick = { onClick.invoke() }, modifier = modifier) {
        Text(text = buttonText)
    }
}