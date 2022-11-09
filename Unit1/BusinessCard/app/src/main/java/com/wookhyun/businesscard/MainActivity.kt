package com.wookhyun.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Phone
import androidx.compose.material.icons.sharp.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wookhyun.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.onSecondary
                ) {
                    Column() {
                        Card(
                            "WookHyun Ha",
                            career = "Android Developer in Daegu Bank(DGB)"
                        )
                        Contact(
                            phone = "010-2402-1051",
                            hashtag = "@HVVH",
                            email = "hyun4911@gmail.com"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Card(name: String, career: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            modifier = Modifier.height(100.dp),
            contentDescription = "android logo",
            contentScale = ContentScale.Fit,
        )
        Text(text = name, color = Color.White, fontSize = 48.sp, textAlign = TextAlign.Justify)
        Text(text = career, color = Color.White, textAlign = TextAlign.Justify)
    }
}

@Composable
fun Contact(phone: String, hashtag: String, email: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {

        ContactRow(icon = Icons.Sharp.Phone, contactString = phone)
        ContactRow(icon = Icons.Sharp.Share, contactString = hashtag)
        ContactRow(icon = Icons.Sharp.Email, contactString = email)
    }
}

@Composable
fun ContactRow(icon: ImageVector, contactString: String) {
    Divider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = Color.White
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(text = contactString, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.onSecondary
        ) {
            Column() {
                Card(
                    "WookHyun Ha",
                    career = "Android Developer in Daegu Bank(DGB)"
                )
                Contact(
                    phone = "010-2402-1051",
                    hashtag = "@HVVH",
                    email = "hyun4911@gmail.com"
                )
            }
        }
    }
}