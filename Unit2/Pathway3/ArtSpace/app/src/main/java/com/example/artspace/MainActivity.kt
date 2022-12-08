package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    var curState by remember { mutableStateOf(0) }

    val paints = arrayListOf(
        Paint(R.drawable.paint1, R.string.title_1, R.string.artist_1, R.string.year_1),
        Paint(R.drawable.paint2, R.string.title_2, R.string.artist_2, R.string.year_2),
        Paint(R.drawable.paint3, R.string.title_3, R.string.artist_3, R.string.year_3)
    )

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
                paint = painterResource(paints[curState].paintId),
            )
        }

        Card(
            elevation = 5.dp,       // 그림자
            modifier = Modifier
                .padding(top = 40.dp)
        ) {
            ArtWorkInfo(
                title = paints[curState].title,
                artist = paints[curState].artist,
                year = paints[curState].year
            )
        }

        Box(
        ) {
            MoveButtons({
                if (curState > 0) {
                    curState--
                }
            }, {
                if (curState < paints.size - 1) {
                    curState++
                }
            })
        }
    }
}

@Composable
fun ArtWorkImage(
    modifier: Modifier = Modifier
        .border(2.dp, Color.Gray)
        .fillMaxWidth(),
    paint: Painter,
    description: String? = null
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
fun MoveButtons(
    onPreBtnClicked: () -> Unit,
    onNextBtnClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        val buttonWidth = 120

        Button(
            onClick = onPreBtnClicked,
            modifier = Modifier.width(buttonWidth.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_pre),
                fontSize = 10.sp,
            )
        }

        Spacer(modifier = Modifier.width(60.dp))

        Button(
            onClick = onNextBtnClicked,
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

data class Paint(
    @DrawableRes val paintId: Int,
    @StringRes val title: Int,
    @StringRes val artist: Int,
    @StringRes val year: Int
)