package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    BusinessCard(
        logo = painterResource(id = R.drawable.android_logo),
        name = stringResource(id = R.string.name),
        title = stringResource(id = R.string.title),
        phoneNumberPainter = painterResource(id = R.drawable.ic_baseline_call_24),
        phoneNumber = stringResource(id = R.string.phone_number),
        sharePainter = painterResource(id = R.drawable.ic_baseline_share_24),
        share = stringResource(id = R.string.share),
        emailPainter = painterResource(id = R.drawable.ic_baseline_email_24),
        email = stringResource(id = R.string.email)
    )
}

@Composable
fun BusinessCard(
    logo: Painter,
    name: String,
    title: String,
    phoneNumberPainter: Painter,
    phoneNumber: String,
    sharePainter: Painter,
    share: String,
    emailPainter: Painter,
    email: String
) {
    Column() {
        Column(
            modifier = Modifier.weight(7f)
        ) {
            BusinessCardTop(
                logo = logo,
                name = name,
                title = title
            )
        }

        Column(
            modifier = Modifier.weight(3f)
        ) {
            BusinessCardInfo(
                phoneNumberPainter = phoneNumberPainter,
                phoneNumber = phoneNumber,
                sharePainter = sharePainter,
                share = share,
                emailPainter = emailPainter,
                email = email
            )
        }
    }
}

@Composable
fun BusinessCardTop(
    logo: Painter,
    name: String,
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp),
        )
        Text(
            text = name,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Composable
fun BusinessCardInfo(
    phoneNumberPainter: Painter,
    phoneNumber: String,
    sharePainter: Painter,
    share: String,
    emailPainter: Painter,
    email: String
) {
    Column {
        val paddingModifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    lineColor,
                    Offset(0f, 0f),
                    Offset(size.width, 0f)
                )
            }
            .padding(start = 48.dp, top = 8.dp, bottom = 8.dp)

        Column(
            modifier = paddingModifier
        ) {
            BusinessCardInfoText(
                logo = phoneNumberPainter,
                body = phoneNumber
            )
        }
        Column(
            modifier = paddingModifier
        ) {
            BusinessCardInfoText(
                logo = sharePainter,
                body = share
            )
        }
        Column(
            modifier = paddingModifier
        ) {
            BusinessCardInfoText(
                logo = emailPainter,
                body = email
            )
        }
    }
}

@Composable
fun BusinessCardInfoText(
    logo: Painter,
    body: String
) {
    Row {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = body,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}