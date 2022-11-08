package com.bpb.and.businesscardapp

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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bpb.and.businesscardapp.ui.theme.BusinessCardAppTheme
import androidx.compose.ui.unit.sp as unitSp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    BusinessCardApp("Android")
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(name: String) {

    Column(modifier = Modifier
        .background(color = Color(0xFF006666))
//        .background(color = Color.Cyan)
        .fillMaxWidth()
//        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.weight(1.8f).fillMaxHeight()
            , horizontalArrangement = Arrangement.Center
            , verticalAlignment = Alignment.Bottom) {
            LogoFullName(name)
        }
        Row(modifier = Modifier.weight(1f)) {
            AdditionalInfo()
        }
    }

}

@Composable
private fun LogoFullName(name: String) {
    Column(
        modifier = Modifier
//            .height(300.dp)
        , verticalArrangement = Arrangement.Bottom
    ) {
        val image = painterResource(R.drawable.android_logo)
        Image(painter = image
            , contentDescription = null
            ,modifier = Modifier
                .padding(top = 80.dp)
                .height(150.dp)
                .width(150.dp)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally)
//                .fillMaxHeight()
//                .fillMaxWidth()
            , contentScale = ContentScale.Fit
        )
        Text(text = "Hello $name!"
            , color = Color(0xFF3ddc84)
//            , fontSize = 16.dp
              , style = TextStyle(
                fontSize = 40.unitSp)
            , modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}

@Composable
private fun AdditionalInfo() {
    Row(modifier = Modifier.fillMaxHeight().padding(bottom = 52.dp)
    , horizontalArrangement = Arrangement.Center
    , verticalAlignment = Alignment.Bottom) {
        Column(modifier = Modifier.weight(1.2f).padding(end = 12.dp)
            , verticalArrangement = Arrangement.Bottom
            , horizontalAlignment = Alignment.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mobile_phone),
                contentDescription = null
            )
            Icon(
                painter = painterResource(id = android.R.drawable.sym_action_call),
                contentDescription = null
            )
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_email),
                contentDescription = null
            )
        }
        Column(modifier = Modifier.weight(3f)
            , verticalArrangement = Arrangement.Bottom
            , horizontalAlignment = Alignment.Start
        ) {


            Text("Mobile: 010-1234-5678", color = Color.White)
            Text("Office: 02) 1234-5678", color = Color.White)
            Text("Email : crystal@google.com", color = Color.White)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardAppTheme {
        BusinessCardApp("Android")
    }
}