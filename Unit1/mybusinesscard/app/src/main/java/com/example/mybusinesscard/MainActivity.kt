package com.example.mybusinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybusinesscard.ui.theme.MyBusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                        CenterArticle()
                        BottomArticle()

                }
            }
        }
    }
}

@Composable
fun CenterArticle() {
    val image = painterResource(id = R.drawable.android_logo)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = null, modifier = Modifier.width(100.dp))
        Text(text = stringResource(id = R.string.full_name), fontSize = 35.sp, color = Color.White)
        Text(text = stringResource(id = R.string.title), color = Color.Green)



    }

}

@Composable
fun BottomArticle() {
    val call = Icons.Default.Call
    val share = Icons.Default.Share
    val message = Icons.Default.Email

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom) {
        Divider(thickness = 1.dp, color = Color.White)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(call, null, modifier = Modifier.weight(1f), tint = Color.White)
            Text(
                text = stringResource(id = R.string.phone),
                color = Color.White,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(thickness = 1.dp, color = Color.White)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(share, null, modifier = Modifier.weight(1f),tint = Color.White)
            Text(
                text = stringResource(id = R.string.sns),
                color = Color.White,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(thickness = 1.dp, color = Color.White)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(message, null, modifier = Modifier.weight(1f),tint = Color.White)
            Text(
                text = stringResource(id = R.string.email),
                color = Color.White,
                modifier = Modifier.weight(2f)
            )
        }
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    MyBusinessCardTheme {
        CenterArticle()
    }
}