package com.ys.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.ys.artspace.R
import com.ys.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var image by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (image) {
            1 -> {
                ArtSpaceScreen(imageResource = R.drawable.guernica,
                    nameResource = R.string.Guernica,
                    authorResource = R.string.Pablo_Picasso,
                    onButtonClick = { image++ },
                    onPreviousButtonClick = { image = 3 })
            }
            2 -> {
                ArtSpaceScreen(imageResource = R.drawable.monalisa,
                    nameResource = R.string.monalisa,
                    authorResource = R.string.Leonardo_da_vinci,
                    onButtonClick = { image++ },
                    onPreviousButtonClick = { image-- })
            }
            3 -> {
                ArtSpaceScreen(imageResource = R.drawable.sunflowers,
                    nameResource = R.string.sunflowers,
                    authorResource = R.string.Vincent_van_gogh,
                    onButtonClick = { image = 1 },
                    onPreviousButtonClick = { image-- })
            }
        }
    }
}


@Composable
fun ArtSpaceScreen(
    imageResource: Int,
    nameResource: Int,
    authorResource: Int,
    onButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                elevation = 15.dp,
                border = BorderStroke(2.dp, color = Color.DarkGray)
            ) {
                Image(painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier.padding(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            Surface(
                elevation = 15.dp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp, 15.dp)
            ) {
                Column {
                    Text(text = stringResource(nameResource),
                        modifier = Modifier.padding(15.dp,15.dp,15.dp,15.dp),
                        fontSize = 30.sp
                    )
                    Text(text = stringResource(authorResource),
                        modifier = Modifier.padding(15.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp, 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onPreviousButtonClick() },
                ) {
                    Text(text = stringResource(R.string.previous))
                }
                Button(
                    onClick = { onButtonClick() },
                ) {
                    Text(text = stringResource(R.string.next))
                }
            }
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