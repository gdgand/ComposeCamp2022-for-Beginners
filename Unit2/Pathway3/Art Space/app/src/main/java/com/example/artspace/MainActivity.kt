package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.RectangleShape
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
                // A surface container using the 'background' color from the theme
                Surface {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(){
    var now by remember { mutableStateOf(1) }

    var prevFun : () -> Unit = {
        if(now > 1) now--
        else now = 6
    }

    var nextFun : () -> Unit = {
        if(now < 5) now++
        else now = 1
    }

    when(now){
        1 -> {  // 강아지 사진
            ArtSpaceMainScreen(
                title = "강아지",
                desc = "웰시코기는 귀여워",
                imageResource = R.drawable.dog,
                prevFun,
                nextFun
            )
        }
        2 -> {  // 고양이 사진
            ArtSpaceMainScreen(
                title = "고양이",
                desc = "아가 냥이는 귀여워",
                imageResource = R.drawable.cat,
                prevFun,
                nextFun
            )
        }
        3 -> {  // 앵무새 사진
            ArtSpaceMainScreen(
                title = "앵무새",
                desc = "화려한 앵무새는 귀여워",
                imageResource = R.drawable.parrot,
                prevFun,
                nextFun
            )
        }
        4 -> {  // 토끼 사진
            ArtSpaceMainScreen(
                title = "토끼",
                desc = "귀가 긴 흰토끼는 귀여워",
                imageResource = R.drawable.rabbit,
                prevFun,
                nextFun
            )
        }
        5 -> {  // 여우 사진
            ArtSpaceMainScreen(
                title = "여우",
                desc = "붉은 여우는 귀여워",
                imageResource = R.drawable.fox,
                prevFun,
                nextFun
            )
        }
        6 -> {  // 돌고래 사진
            ArtSpaceMainScreen(
                title = "돌고래",
                desc = "맨들한 돌고래는 귀여워",
                imageResource = R.drawable.dolphin,
                prevFun,
                nextFun
            )
        }
    }
}

@Composable
fun ArtSpaceMainScreen(
    title: String,
    desc: String,
    imageResource : Int,
    prevFun: () -> Unit,
    nextFun: () -> Unit
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewImages(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .weight(5f)
                .border(
                    1.dp, Color.Black, RectangleShape
                )
            ,
            imageResource
        )
        Spacer(modifier = Modifier.height(16.dp))
        TitleAndDesc(
            Modifier
                .fillMaxSize()
                .weight(1f),
            title,
            desc
        )
        Spacer(modifier = Modifier.height(16.dp))
        Buttons(
            Modifier
                .fillMaxSize()
                .weight(1f),
            prevFun,
            nextFun
        )
    }
}

@Composable
fun viewImages(modifier: Modifier = Modifier, imageResource: Int) {
    Column(
        modifier = modifier
            .padding(20.dp),
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun TitleAndDesc(modifier: Modifier = Modifier, title: String, desc: String){
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Text(
            text = desc,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Buttons(modifier: Modifier = Modifier, prevFun: () -> Unit, nextFun: () -> Unit){
    Row(
        modifier = modifier
            .padding(20.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = prevFun,
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                text = "< PREV",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = nextFun,
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                text = "NEXT >",
                fontSize = 20.sp
            )
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