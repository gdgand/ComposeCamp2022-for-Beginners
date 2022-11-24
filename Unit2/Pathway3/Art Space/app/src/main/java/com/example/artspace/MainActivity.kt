package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
    PhotoFrame()
}

@Composable
fun PhotoFrame(modifier: Modifier = Modifier) {
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
                painter = painterResource(R.drawable.lemon_tree),
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
            Button( onClick = {}) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {}) {
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