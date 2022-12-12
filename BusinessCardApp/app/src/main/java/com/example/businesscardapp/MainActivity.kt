package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.R.*
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme
import com.example.businesscardapp.ui.theme.background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
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
    Column(
        modifier = Modifier
            .background(color = background)
            .fillMaxSize()
    ) {
        BusinessCardInfo(
            logo = painterResource(id = drawable.android_logo),
            name = stringResource(id = string.name),
            title = stringResource(id = string.title),
            modifier = Modifier
                .weight(3f)
        )
        ContractInfo(
            phoneNumber = stringResource(id = string.phoneNumber),
            sns = stringResource(id = string.sns),
            email = stringResource(id = string.email),
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun BusinessCardInfo(
    logo: Painter,
    name: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = logo,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = name,
            fontSize = 48.sp,
            fontWeight = FontWeight.Thin,
            color = Color.White
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier
                .padding(top = 10.dp)
        )
    }

}
@Composable
private fun ContractInfo(
    phoneNumber: String,
    sns: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = Color.DarkGray))
        Row() {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = phoneNumber,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 25.dp, top = 5.dp, bottom = 5.dp)
            )
        }
        Spacer(Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = Color.DarkGray))
        Row() {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = sns,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 25.dp, top = 5.dp, bottom = 5.dp)
            )
        }
        Spacer(Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = Color.DarkGray))
        Row() {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = email,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 25.dp, top = 5.dp, bottom = 5.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    BusinessCardAppTheme {
        BusinessCardApp()
    }
}