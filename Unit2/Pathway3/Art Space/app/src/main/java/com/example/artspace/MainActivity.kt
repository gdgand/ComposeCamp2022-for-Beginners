package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var position by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Surface(
            border = BorderStroke(
                width = 3.dp,
                color = Color.Gray
            ),
            elevation = 10.dp,
            modifier = Modifier
                .heightIn(0.dp, 300.dp)
                .align(alignment = Alignment.CenterHorizontally)

        ) {
           GetImg(position = position)
        }
        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            elevation = 10.dp
        ) {
            TitleArtist(position)
        }
        MakeBtn(position = position, changePos = {position = it})
    }
}

@Composable
fun GetImg(position:Int){
    val imgRes = when(position){
        0 -> R.drawable.tree
        1 -> R.drawable.tree_v
        else -> R.drawable.fire
    }
    
    Image(
        painter = painterResource(imgRes),
        contentDescription = imgRes.toString(),
        modifier = Modifier.padding(30.dp)
    )
}

@Composable
fun TitleArtist(position:Int){
    val title = when(position){
        0 -> R.string.title0
        1 -> R.string.title1
        else -> R.string.title2
    }
    val artist = when(position){
        0 -> R.string.artist0
        1 -> R.string.artist1
        else -> R.string.artist2
    }
    val year = when(position){
        0 -> R.string.year0
        1 -> R.string.year1
        else -> R.string.year2
    }
    
    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Thin,
            fontSize = 40.sp,
        )
        Row() {
            Text(
                text = stringResource(id = artist),
                fontWeight = FontWeight.ExtraBold
            )
            Text(text = stringResource(id = year))
        }
    }
}

@Composable
fun MakeBtn(position: Int, changePos:(Int) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            contentPadding = PaddingValues(horizontal = 40.dp),
            onClick = { changePos((position+3-1)%3) }
        ) {
            Text(text = stringResource(id = R.string.previous))
        }

        Button(
            contentPadding = PaddingValues(horizontal = 55.dp),
            onClick = { changePos((position+1)%3) }
        ) {
            Text(text = stringResource(id = R.string.next))
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