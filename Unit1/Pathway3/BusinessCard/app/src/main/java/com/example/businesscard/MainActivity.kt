package com.example.businesscard

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                Surface {
                    TotalIntro()
                }
            }
        }
    }
}

@Composable
fun TotalIntro() {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
    ) {
        MainIntro(
            stringResource(R.string.user_name),
            stringResource(R.string.introduce),
            modifier = Modifier.weight(3f)
        )
        SubIntro(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MainIntro(name: String, title: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_android),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.os),
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = name,
            fontSize = 35.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3ddc84)
        )
    }
}

@Composable
fun SubIntroItem(painter: Painter, info: String) {
    Row {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 100.dp)
                .weight(1.5f)
        )
        Text(
            text = info,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .weight(2f)
        )
    }
}

@Composable
fun SubIntro(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        SubIntroItem(painterResource(R.drawable.ic_phone), stringResource(R.string.phone))
        SubIntroItem(painterResource(R.drawable.ic_share), stringResource(R.string.share))
        SubIntroItem(painterResource(R.drawable.ic_email), stringResource(R.string.email))
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Surface {
            TotalIntro()
        }
    }
}