package com.example.artspace

import android.os.Bundle
import android.widget.Space
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

    var currentPage by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (currentPage) {
            1 -> {
                TopImage(painterResource(id = R.drawable.shiba))

                TitleAndDesc(
                    stringResource(id = R.string.shiba),
                    stringResource(id = R.string.shiba_desc)
                )
            }

            2 -> {
                TopImage(painterResource(id = R.drawable.shepherd))

                TitleAndDesc(
                    stringResource(id = R.string.shepherd),
                    stringResource(id = R.string.shepherd_desc)
                )
            }

            3 -> {
                TopImage(painterResource(id = R.drawable.sichu))

                TitleAndDesc(
                    stringResource(id = R.string.sichu),
                    stringResource(id = R.string.sichu_desc)
                )
            }

            4 -> {
                TopImage(painterResource(id = R.drawable.biggle))

                TitleAndDesc(
                    stringResource(id = R.string.biggle),
                    stringResource(id = R.string.biggle_desc)
                )
            }

        }

        Buttons(
            { if (currentPage == 1) currentPage = 4 else currentPage-- },
            { if (currentPage == 4) currentPage = 1 else currentPage++ }
        )
    }

}

@Composable
fun TopImage(
    painter: Painter
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(500.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .border(3.dp, Color.Black, RectangleShape)
                .padding(16.dp)

        )

    }

}

@Composable
fun TitleAndDesc(
    title: String,
    desc: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray, RectangleShape)
        ) {
            Text(
                text = title,
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )


            Text(
                text = desc,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }


}

@Composable
fun Buttons(
    clickPrev: () -> Unit,
    clickNext: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = clickPrev, modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = clickNext, modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
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
        ArtSpaceApp()
    }
}