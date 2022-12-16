package com.example.namecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namecard.ui.theme.NameCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NameCardApp(name = "Kim")
                }
            }
        }
    }
}

@Composable
fun NameCardApp(name: String) {
    Column()
    {
        profile(
            name = stringResource(R.string.name),
            desc = stringResource(R.string.desc),
            modifier = Modifier.weight(0.6f)
        )

        info(
            call = stringResource(R.string.call),
            id = stringResource(R.string.id),
            email = stringResource(R.string.email),
            modifier = Modifier.weight(0.4f)
        )
    }
}

@Composable
fun profile(
    name: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val image = painterResource(id = R.drawable.android_logo)
        Box(modifier = Modifier.padding(start = 120.dp, end = 120.dp))
        {
            Image(
                painter = image,
                contentDescription = null
            )
        }
        Text(
            text = name,
            fontSize = 36.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Text(
            text = desc,
            fontSize = 16.sp,
            color = Color(0xFF3ddc84),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun info(
    call: String,
    id: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Icon(
                Icons.Filled.Call,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = call,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Icon(
                Icons.Filled.Share,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = id,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Icon(
                Icons.Filled.Email,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = email,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    NameCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.DarkGray
        ) {
            NameCardApp(name = "Kim")
        }
    }
}