package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
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
                PhotoFrame()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        PhotoFrame()
    }
}

@Composable
fun PhotoFrame(modifier: Modifier = Modifier) {

    val myRes = listOf<ImageRes>(
        ImageRes(R.drawable.lemon_tree, "",""),
        ImageRes(R.drawable.lemon_restart, "",""),
        ImageRes(R.drawable.lemon_drink, "",""),
        ImageRes(R.drawable.lemon_squeeze, "","")
    )

    var idx by remember { mutableStateOf(0) }
    var art: ImageRes = myRes[idx]


    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally

    ) {
        when (idx) {
            1 -> {    println("prame1")}
            2 -> {    println("prame2")}
        }

        Box(modifier = modifier
            .shadow(5.dp)
            .padding(10.dp)
        ) {
            Image(
                painter = painterResource(art.imageId),
                contentDescription = " ",
                modifier = modifier
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Artwork Title", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "SubTitle", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.wrapContentSize(Alignment.Center)
        ) {
            Button( onClick = {
                idx --
                println("Previous")
            }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                idx ++
                println("Next")
            }) {
                Text("Next")
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