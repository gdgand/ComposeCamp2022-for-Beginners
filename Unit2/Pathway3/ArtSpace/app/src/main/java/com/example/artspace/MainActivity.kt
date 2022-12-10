package com.example.artspace

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
                    ArtSpaceScreen(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {

        ArtImageUI(imgId = R.drawable.apple_workplace, stringId = R.string.str_android)
    }
}

@Composable
fun ArtImageUI(imgId: Int, stringId: Int) {
    Box(modifier = Modifier.border(
        width = 5.dp,
        color = Color.Gray,
        shape = RectangleShape
    )) {
        Image(
            modifier = Modifier
                .padding(20.dp),
            painter = painterResource(imgId),
            contentDescription = stringResource(id = stringId)
        )
    }
}

@Composable
fun ArtImgContent(stringId: Int) {

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen(modifier = Modifier)
    }
}