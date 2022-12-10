package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Center
    ) {
        Information()
        Detail()
    }
}

@Composable
fun Information() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5F),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "android logo",
            modifier = Modifier.size(100.dp)
        )
        Text(
            fontSize = 36.sp,
            color = Color.White,
            text = stringResource(R.string.full_name)
        )
        Text(
            text = stringResource(id = R.string.title),
            color = Color(0xFF3ddc84)
        )
    }
}

@Composable
fun Detail() {
    Column(
        modifier = Modifier
            .padding(30.dp, 30.dp, 30.dp, 50.dp)
            .fillMaxWidth()
            .fillMaxHeight(1F),
        verticalArrangement = Arrangement.Bottom
    ) {

        Divider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Rounded.Call,
                contentDescription = "phone",
                tint = Color(0xFF3ddc84)
            )
            Spacer(Modifier.width(50.dp))
            Text(
                stringResource(id = R.string.phone),
                color = Color.White
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Rounded.Share,
                contentDescription = "share",
                tint = Color(0xFF3ddc84)
            )
            Spacer(Modifier.width(50.dp))
            Text(
                '@'+stringResource(R.string.sns),
                color = Color.White
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .padding(30.dp, 10.dp, 30.dp, 50.dp)
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Rounded.Email,
                contentDescription = "email",
                tint = Color(0xFF3ddc84)
            )
            Spacer(Modifier.width(50.dp))
            Text(
                stringResource(R.string.email),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            BusinessCardApp()
        }
    }
}