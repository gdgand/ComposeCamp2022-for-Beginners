package com.example.happybirthday

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BirthdayGreetingWithImage(stringResource(id = R.string.happy_birthday_text), stringResource(
                        id = R.string.signature_text))
                }
            }
        }
    }
}

// 7. 텍스트 정렬 및 패딩 추가
@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
    // Create a column so that texts don't overlap
    Column {
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 400.dp)
        )
        Text(
            text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 36.dp)
        )
    }
}

// 5. Box 레이아웃 추가
@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {
    val image = painterResource(R.drawable.ic_birthday)

    Box {
        Image(painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = message, from = from)
    }
}

// 4. 이미지 컴포저블 추가
@Preview(showBackground = false)
@Composable
private fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        BirthdayGreetingWithImage(stringResource(id = R.string.happy_birthday_text), stringResource(
            id = R.string.signature_text))
    }
}