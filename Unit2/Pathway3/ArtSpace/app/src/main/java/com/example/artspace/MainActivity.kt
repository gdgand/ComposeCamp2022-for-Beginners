package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                    ArtDisplay()
                }
            }
        }
    }

@Composable
fun ArtDisplay() {
    var current by remember { mutableStateOf(1) }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)) {
        when(current)
        {
            1->{
                ArtImageAndText(image = R.drawable.river, title = R.string.title_river, artist = R.string.painter_river, year = R.string.year_river)
            }
            2->{
                ArtImageAndText(image = R.drawable.pink_sky, title = R.string.title_sky, artist = R.string.painter_sky, year = R.string.year_sky)
            }
            3-> {
                ArtImageAndText(image = R.drawable.snow, title = R.string.title_snow, artist = R.string.painter_snow, year = R.string.year_snow)
            }
            else -> {
                ArtImageAndText(image = R.drawable.snow, title = R.string.title_snow, artist = R.string.painter_snow, year = R.string.year_snow)
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(modifier = Modifier.padding(10.dp,10.dp)) {
            Button(modifier = Modifier.weight(1f), onClick = {if (current!=1) current--}){
                Text(text = stringResource(id = R.string.previous))
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(modifier = Modifier.weight(1f),onClick = { if (current!=3) current++ }) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun ArtImageAndText(image:Int,title:Int,artist:Int,year:Int) {
    Column() {
        Surface(modifier = Modifier
            .shadow(elevation = 5.dp)
            .border(BorderStroke(3.dp, Color.Gray), shape = RectangleShape)
        ){
        Image(modifier = Modifier.wrapContentSize().padding(25.dp),painter = painterResource(id = image), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(35.dp))
        Surface(modifier = Modifier
            .shadow(elevation = 5.dp)
            .background(Color.White)
            .fillMaxWidth()
            .padding(15.dp)) {
            Column() {
                Text(text = stringResource(id = title), fontSize = 24.sp, fontWeight = FontWeight.Light)
                Row() {
                    Text(text = stringResource(id = artist), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = stringResource(id = year), color = Color.Gray,fontWeight = FontWeight.Light)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtDisplay()
    }
}