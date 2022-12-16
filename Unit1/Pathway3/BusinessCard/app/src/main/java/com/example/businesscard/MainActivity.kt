package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(R.color.background)
                ) {
                    BusinessCard()
                }
            }
        }
    }
}


@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(R.color.background)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box() {}
        MiddleArea()
        BottomArea()
    }
}

@Composable
fun MiddleArea() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.android_logo), contentDescription = null, modifier = Modifier
            .height(100.dp)
            .width(100.dp))
        Text(text = "Jennifer Doe", fontSize = 48.sp, color = Color.White)
        Text(text = "Android Developer Extraordinaire", fontWeight = FontWeight.Bold, color = Color(0xFF4edb9d))
    }
}

@Composable
fun BottomArea() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DataRow(image = painterResource(id = R.drawable.ic_baseline_call_24), value = "+11 (123) 444 555 666")
        DataRow(image = painterResource(id = R.drawable.ic_baseline_share_24), value = "@AndroidDev")
        DataRow(image = painterResource(id = R.drawable.ic_baseline_email_24), value = "jen.doe@android.com")
    }
}

@Composable
fun DataRow(image: Painter, value: String) {
    Divider(color = Color.White, thickness = 0.5.dp)
    Row(modifier = Modifier.padding(top = 10.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)) {
        Icon(painter = image, contentDescription = null, tint = Color(0xFF4edb9d))
        Text(text = value, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth(), color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}