package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            ArtWorkImage(
                paint = painterResource(id = R.drawable.paint2),
            )
        }

        Card(
            elevation = 5.dp,       // 그림자
            modifier = Modifier
                .padding(top = 40.dp)
        ) {
            ArtWorkInfo(
                title = R.string.title_2,
                artist = R.string.artist_2,
                year = R.string.year_2
            )
        }

        Box(
        ) {
            MoveButtons()
        }
    }
}

@Composable
fun ArtWorkImage(
    paint: Painter,
    description: String? = null,
    modifier: Modifier = Modifier
        .border(2.dp, Color.Gray)
        .fillMaxWidth()

) {
    Image(
        painter = paint,
        contentDescription = description,
        modifier = modifier.padding(20.dp),
        contentScale = ContentScale.FillWidth
    )

}

@Composable
fun ArtWorkInfo(
    @StringRes title: Int,
    @StringRes artist: Int,
    @StringRes year: Int
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 24.sp
        )

        Row(

        ) {
            Text(
                text = stringResource(id = artist),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = year)
            )
        }
    }

}

@Composable
fun MoveButtons() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        val buttonWidth = 120
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(buttonWidth.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_pre),
                fontSize = 10.sp,
            )
        }

        Spacer(modifier = Modifier.width(60.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(buttonWidth.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_next),
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}