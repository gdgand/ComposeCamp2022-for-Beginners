package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    elevation = 6.dp
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var position by remember {
        mutableStateOf(1)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (position) {
            1 -> {
                Album(
                    image = R.drawable.horses_2904536_1920,
                    description = R.string.description_horse,
                    Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                )

                ImageTitle(R.string.description_horse, R.string.title_horse)
            }
            2 -> {
                Album(
                    image = R.drawable.building,
                    description = R.string.description_building,
                    Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                )

                ImageTitle(R.string.description_building, R.string.title_building)
            }
            3 -> {
                Album(
                    image = R.drawable.desert,
                    description = R.string.description_desert,
                    Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                )

                ImageTitle(R.string.description_desert, R.string.title_desert)
            }
            4 -> {
                Album(
                    image = R.drawable.amethyst,
                    description = R.string.description_amethyst,
                    Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                )

                ImageTitle(R.string.description_amethyst, R.string.title_amethyst)
            }
            else -> {
                Album(
                    image = R.drawable.twilight,
                    description = R.string.description_twilight,
                    Modifier.weight(1f)
                )
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                )

                ImageTitle(R.string.description_twilight, R.string.title_twilight)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(modifier = Modifier.weight(1f), onClick = {
                position--
                if (position == 0)
                    position = 5


            }) {
                Text(text = "Previous")
            }
            Surface(modifier = Modifier.width(10.dp)) {

            }
            Button(modifier = Modifier.weight(1f), onClick = {
                if (++position > 5)
                    position = 1
            }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun Album(image: Int, description: Int, modifier: Modifier) {
    Surface(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .border(3.dp, color = Color(153, 153, 153)),
        elevation = 2.dp
    ) {
        Image(
            modifier = Modifier.padding(30.dp),
            painter = painterResource(id = image),
            contentDescription = stringResource(id = description)
        )
    }

}

@Composable
fun ImageTitle(description: Int, imageTitle: Int) {
    Surface(elevation = 10.dp) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(20.dp), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = imageTitle), fontWeight = FontWeight.Bold
            )
            Text(text = stringResource(id = description))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}