package com.example.namecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namecard.ui.theme.NameCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NameCard()
                }
            }
        }
    }
}

/*@Composable
fun MyTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}*/

@Preview(showBackground = false)
@Composable
fun NameCard() {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Top)
                .background(color = Color(0xFF003A49)),
        ) {
            Column {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(3f, true)
                ) {
                    ProfileBlock(
                        imgSrc = R.drawable.android_logo,
                        fullName = "Dan Brown",
                        title = "Android Developer Extraordinaire"
                    )
                }
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.weight(1f, true)) {
                    ContentBlock()
                }
            }
        }
}

@Composable
fun ProfileBlock(imgSrc: Int, fullName: String, title: String) {
        val AndroidGreen = Color(0xFF48dc9a)
        val profileImg = painterResource(id = imgSrc)
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row {
                Image(
                    painter = profileImg,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
            Row {
                Text(
                    text = fullName,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 40.sp,
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
            Row {
                Text(
                    text = title,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 10.sp,
                        color = AndroidGreen,
                        fontWeight = FontWeight(500),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
}

@Composable
fun ContentBlock() {
        val AndroidGreen = Color(0xFF48dc9a)
        val defaultColor = Color(0xFFFFFFFF)
        val iconPadding = Modifier.padding(start = 40.dp, end = 20.dp)
        val rowModifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.White,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .padding(all = 5.dp)
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = rowModifier
            ) {
                Icon(
                    Icons.Rounded.Call,
                    contentDescription = "telephone icon",
                    modifier = iconPadding,
                    tint = AndroidGreen
                )
                Text(
                    text = "+82 010 1544 3434",
                    color = defaultColor
                )
            }
            Row(
                modifier = rowModifier
            ) {
                Icon(
                    Icons.Rounded.Share,
                    contentDescription = "share icon",
                    modifier = iconPadding,
                    tint = AndroidGreen
                )
                Text(
                    text = "@social media link",
                    color = defaultColor,
                )
            }
            Row(
                modifier = rowModifier
            ) {
                Icon(
                    Icons.Rounded.Email,
                    contentDescription = "email icon",
                    modifier = iconPadding,
                    tint = AndroidGreen
                )
                Text(
                    text = "email@domain.com",
                    color = defaultColor,
                )
            }
        }
}