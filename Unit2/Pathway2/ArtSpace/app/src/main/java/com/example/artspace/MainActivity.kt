package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var index by remember {
        mutableStateOf(1)
    }
    val max_index = 3
    val min_index = 1
    var art = 0
    var title = 0
    var artist = 0
    var year = 0
    when(index) {
        1 -> {
            art = R.drawable.cat1
            title = R.string.title1
            artist = R.string.artist1
            year = R.string.year1
        }
        2 -> {
            art = R.drawable.cat2
            title = R.string.title2
            artist = R.string.artist2
            year = R.string.year2
        }
        else -> {
            art = R.drawable.cat3
            title = R.string.title3
            artist = R.string.artist3
            year = R.string.year3
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ArtFrame(
            art = art,
            title = title,
            artist = artist,
            year = year
        )
        Spacer(modifier = Modifier.height(15.dp))
        ControlBox(
            onPrev = {
                if(index < min_index) {
                    index = max_index
                } else {
                    index -= 1
                }
                     },
            onNext = {
                if(index >= max_index) {
                    index = min_index
                } else {
                    index += 1
                }
                     },
        )
    }
}

@Composable
fun ArtFrame(
    @DrawableRes
    art: Int,
    @StringRes
    title: Int,
    @StringRes
    artist: Int,
    @StringRes
    year: Int,
) {
    val painter = painterResource(art)
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painter, contentDescription = null)
        Spacer(Modifier.height(20.dp))
        TitleBox(
            title = title,
            artist = artist,
            year = year
        )
    }
}

@Composable
fun TitleBox(
    @StringRes
    title: Int,
    @StringRes
    artist: Int,
    @StringRes
    year: Int,
) {
    val info = stringResource(id = artist) + "(" +stringResource(id = year) + ")"
    Surface(
        elevation = 3.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                stringResource(id = title),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                info,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun ControlBox(
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = onPrev) {
            Text(stringResource(R.string.prev_button_text))
        }
        Spacer(Modifier.width(8.dp))
        Button(onClick = onNext) {
            Text(stringResource(R.string.next_button_text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}

@Preview(showBackground = true)
@Composable
fun ControlBoxPreview() {
    ArtSpaceTheme {
        ControlBox(
            onPrev = {},
            onNext = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtFramePreview() {
    ArtSpaceTheme {
        ArtFrame(
            art = R.drawable.cat1,
            title = R.string.title1,
            artist = R.string.artist1,
            year = R.string.year1
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TitleBoxPreview() {
    ArtSpaceTheme {
        TitleBox(
            title = R.string.title1,
            artist = R.string.artist1,
            year = R.string.year1
        )
    }
}