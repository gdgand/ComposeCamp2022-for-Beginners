package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.Background
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
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
        Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        ProfileView(
            image = R.drawable.android_logo,
            "Charlie Moon",
            "Android Developer Extraordinaire",
            140.dp
        )
        InfoList()
    }
}

@Composable
fun InfoList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp), verticalArrangement = Arrangement.Bottom
    ) {
        InfoRow(image = R.drawable.ic_baseline_call_24, text = "+82-10-1234-1234")
        Divider(color = Color.White, thickness = 2.dp)
        InfoRow(image = R.drawable.ic_baseline_share_24, text = "@AndroidDev")
        Divider(color = Color.White, thickness = 2.dp)
        InfoRow(image = R.drawable.ic_baseline_email_24, text = "Charlie@android.com")
    }

}

@Composable
fun ProfileView(image: Int, name: String, title: String, padding: Dp) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.padding(top = padding, start = padding, end = padding)
        )
        Text(text = name, fontSize = 45.sp, color = Color.White, fontWeight = FontWeight(20))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = title, color = Color(0xFF3ddc84), fontWeight = FontWeight.Bold)
    }
}


@Composable
fun InfoRow(@DrawableRes image: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = null,
            tint = Color(0xFF3ddc84)
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(text = text, color = Color.White, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}