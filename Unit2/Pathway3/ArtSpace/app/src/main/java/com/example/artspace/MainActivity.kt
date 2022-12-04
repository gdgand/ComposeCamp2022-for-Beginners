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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    ArtSpaceScreen()
                }
            }
        }
    }
}


private fun getPicture(picNum: Int): Int{
    return when(picNum){
        0-> R.drawable.img1
        1-> R.drawable.img2
        2-> R.drawable.img3
        3-> R.drawable.img4
        4-> R.drawable.img5
        else -> R.drawable.img6
    }
}
private fun getPaintTitle(picNum: Int): Int{
    return when(picNum){
        0-> R.string.img1_title
        1-> R.string.img2_title
        2-> R.string.img3_title
        3-> R.string.img4_title
        4-> R.string.img5_title
        else -> R.string.img6_title
    }
}

private fun getPaintDesc(picNum: Int): Int{
    return when(picNum){
        0-> R.string.img1_desc
        1-> R.string.img2_desc
        2-> R.string.img3_desc
        3-> R.string.img4_desc
        4-> R.string.img5_desc
        else -> R.string.img6_desc
    }
}

private fun getPaintDescYear(picNum: Int): Int{
    return when(picNum){
        0-> R.string.img1_desc_year
        1-> R.string.img2_desc_year
        2-> R.string.img3_desc_year
        3-> R.string.img4_desc_year
        4-> R.string.img5_desc_year
        else -> R.string.img6_desc_year
    }
}

@Composable
fun ArtSpaceScreen() {
    var page by remember { mutableStateOf(4) }
    var picNum by remember { mutableStateOf(0) }

    var paint = getPicture(picNum)
    var paintTitle = getPaintTitle(picNum)
    var paintDesc = getPaintDesc(picNum)
    var paintDescYear = getPaintDescYear(picNum)

    val setPicNum: (Int) -> Unit = { it -> picNum = it}


    Column(
        modifier = Modifier.fillMaxSize().padding(0.dp,10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.border(BorderStroke(4.dp, Color(128, 128, 128))
            )

        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(0.7f)
                    .padding(30.dp),
                painter = painterResource(paint),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.wrapContentSize(Alignment.Center).padding(0.dp,20.dp)

                .border(BorderStroke(1.dp, Color(230, 230, 230)))

        ) {
            Text(
                text = stringResource(paintTitle),
                fontSize = 18.sp,
                modifier = Modifier.absolutePadding(10.dp,15.dp,10.dp,1.dp)

            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(paintDesc),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.absolutePadding(10.dp, 1.dp, 0.dp, 15.dp)
                )
                Text(
                    text = stringResource(paintDescYear),
                    fontSize = 14.sp,
                    modifier = Modifier.absolutePadding(3.dp, 1.dp, 10.dp, 15.dp)
                )
            }


        }
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = { setPicNum(if(picNum<0) picNum+5 else picNum-1) }){ Text("Previous")}
            Button(onClick = { setPicNum((picNum+1)%6) }){ Text("Next")}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}