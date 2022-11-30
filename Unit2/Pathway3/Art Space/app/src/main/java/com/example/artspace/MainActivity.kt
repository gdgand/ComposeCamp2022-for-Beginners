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

    val myRes = listOf (
        ImageRes(R.drawable.lemon_tree, "LEMON TREE","artist1"),
        ImageRes(R.drawable.lemon_restart, "LEMON TREE R","artist2"),
        ImageRes(R.drawable.lemon_drink, "LEMON DRINK","artist3"),
        ImageRes(R.drawable.lemon_squeeze, "LEMON SQUEEZE","artist4")
    )

    var idx by remember { mutableStateOf(0) }
    var art: ImageRes = myRes[idx]


    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(modifier = modifier
            .shadow(5.dp)
            .padding(10.dp)
        ) {
            Image(
                painter = painterResource(art.imageId),
                contentDescription = "",
                modifier = modifier
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = art.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = art.subTitle, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.wrapContentSize(Alignment.Center)
        ) {
            Button( onClick = {
                if(idx == 0) idx = myRes.size - 1
                else idx --
            }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if(idx == myRes.size - 1) idx = 0
                else idx ++
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