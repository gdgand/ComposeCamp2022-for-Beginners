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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                BusinessCard()
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BusinessCard() {
    Column (
        modifier = Modifier
            .background(Color(0xFF27413E))
    ) {
        Box(Modifier.weight(1f)) {
            MainInfo()
        }
        ContactDetails()
    }
}

// @Preview(showBackground = true)
@Composable
fun MainInfo() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            Modifier.size(120.dp)
        )
        Text(
            text = stringResource(R.string.full_name),
            fontSize = 48.sp,
            //modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC2E4C4)
        )
        Text(
            text = stringResource(R.string.title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic,
            color = Color(0xFF3ddc84)
        )
    }
}

// @Preview(showBackground = true)
@Composable
fun ContactDetails() {
    Column(
        Modifier
            //.fillMaxSize()
            .fillMaxWidth()
            .padding(bottom = 36.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        ContactRow(
            painterResource(id = R.drawable.ic_baseline_local_phone_24),
            stringResource(id = R.string.ic_phone_number),
            "+82 (10) 1234 1234"
        )

        ContactRow(
            painterResource(id = R.drawable.ic_baseline_share_24),
            stringResource(id = R.string.ic_share),
            "@socialmediahandle",
        )

        ContactRow(
            painterResource(id = R.drawable.ic_baseline_email_24),
            stringResource(id = R.string.ic_email),
            "hhyeok1026@gmail.com"
        )
    }
}

@Composable
fun ContactRow(
    ic_image: Painter,
    ic_contentDescription: String,
    contactText: String,
) {
    Divider(color = Color(0xFF7A887A), thickness = 1.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
    ) {
        Spacer(Modifier.width(48.dp))
        Icon(
            painter = ic_image,
            contentDescription = ic_contentDescription,
            tint = Color(0xff3DDC84),
        )
        Spacer(Modifier.width(24.dp))
        Text(
            text = contactText,
            color = Color(0xFFC2E4C4),
            fontSize = 16.sp
        )
    }
}