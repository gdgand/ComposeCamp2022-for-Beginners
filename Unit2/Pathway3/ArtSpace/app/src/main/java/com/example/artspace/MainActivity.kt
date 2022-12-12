package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.data.DataSet
import com.example.artspace.model.ArtItem
import com.example.artspace.ui.theme.ArtSpaceTheme

val paddingDp = 16.dp

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
                    ArtworkApp()
                }
            }
        }
    }
}

@Composable
fun ArtworkApp() {
    var position by remember { mutableStateOf(0) }
    val artList: List<ArtItem> = DataSet.artItem

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                // 남은 전체 크기를 가져감
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .border(color = Color.Gray, width = 2.dp)
                    .padding(paddingDp)
            ) {
                Image(
                    painter = painterResource(artList[position].painterId),
                    contentDescription = null
                )
            }
        }
        Column {
            Surface(
                modifier = Modifier
                    .padding(paddingDp)
                    .shadow(
                        elevation = 8.dp
                    )
            ) {
                TitleAndArtistText(
                    title = artList[position].title,
                    artist = artList[position].artist,
                    year = artList[position].year
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageChangeButton(
                title = stringResource(id = R.string.previous_button),
                click = {
                    if (position == 0) position = artList.size - 1 else position--
                }
            )
            ImageChangeButton(
                title = stringResource(id = R.string.next_button),
                click = {
                    if (position == artList.size - 1) position = 0 else position++
                }
            )
        }
    }
}

@Composable
fun TitleAndArtistText(
    title: String,
    artist: String,
    year: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(paddingDp)) {
        Text(
            text = title,
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("$artist ")
                }
                withStyle(
                    SpanStyle(
                        color = Color.Gray
                    )
                ) {
                    append("($year)")
                }
            }
        )
    }
}

@Composable
fun ImageChangeButton(
    title: String,
    click: () -> Unit
) {
    Button(onClick = click) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtworkApp()
    }
}
