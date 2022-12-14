package com.example.artspace

import android.graphics.Paint.Align
import android.os.Bundle
//import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
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
                    //del Greeting("Android")
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    var artImages = mutableListOf<Int>(R.drawable.eong1, R.drawable.eong2, R.drawable.eong3)
    var artTitles = mutableListOf<Int>(R.string.eong1, R.string.eong2, R.string.eong3)
    var artDescs = mutableListOf<Int>(R.string.eong1_desc, R.string.eong2_desc, R.string.eong3_desc)
    var imageNo by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = artImages[imageNo]),
            contentDescription = stringResource(id = artDescs[imageNo]),
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Gray))
                .padding(30.dp)
                .fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text( //artImages.size.toString(), //
                text = stringResource(id = artTitles[imageNo]),
                fontSize = 37.sp,
                fontWeight = FontWeight.Bold
        )
        Text(
                text = stringResource(id = artDescs[imageNo]),
                fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row() {
            Button(onClick = {
                imageNo -= 1
                if (imageNo < 0) {
                    imageNo = artImages.size - 1
                }
            }) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                imageNo += 1
                if (imageNo >= artImages.size) {
                    imageNo = 0
                }
            }) {
                Text(text = "Next")
            }
        }
    }
}
/* del
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
 */

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        //del Greeting("Android")
        ArtSpaceApp()
    }
}