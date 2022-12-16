package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class MasterPiece(
    var title: Int,
    var artist: Int,
    var year: Int,
    @DrawableRes var image: Int
)

object ArtCollection {
    var artofWorks = listOf(
        MasterPiece(
            title = R.string.artwork1Title,
            artist = R.string.artwork1Artist,
            year = R.string.artwork1Year,
            R.drawable.image01
            ),
        MasterPiece(
            title = R.string.artwork2Title,
            artist = R.string.artwork2Artist,
            year = R.string.artwork2Year,
            R.drawable.image02
        ),
        MasterPiece(
            title = R.string.artwork3Title,
            artist = R.string.artwork3Artist,
            year = R.string.artwork3Year,
            R.drawable.image03
        ),
    )

}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtWorkScreen()
                }
            }
        }
    }
}

@Composable
fun ArtWorkScreen() {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var currentPiece: MasterPiece = ArtCollection.artofWorks.first()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        when (currentStep) {
            1 -> currentPiece = ArtCollection.artofWorks.first()
            2 -> currentPiece = ArtCollection.artofWorks.get(currentStep-1)
            3 -> currentPiece = ArtCollection.artofWorks.get(currentStep-1)
        }

        Text(style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = "art space")
        Spacer(modifier = Modifier.height(32.dp))
        Artwork(piece = currentPiece)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {

                if ((currentStep - 1) <= 0){
                    currentStep = 3
                }else{
                    currentStep = currentStep - 1
                }

            }) {
                Text(stringResource(R.string.previous))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if ((currentStep + 1) > 4) {
                    currentStep = 1
                }else{
                    currentStep = currentStep + 1
                }

            }) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun Artwork(piece: MasterPiece) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(id = piece.image),
            contentDescription = stringResource(id = piece.title)
        )
        Column() {
            Text(stringResource(id = piece.title))
            Row() {
                Text(stringResource(id = piece.artist))
                Text("("+stringResource(id = piece.year)+")")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtWorkScreen()
    }
}