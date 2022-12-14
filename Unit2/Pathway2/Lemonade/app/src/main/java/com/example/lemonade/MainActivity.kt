package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}
@Composable
fun WelcomeScreen(name : String, onStartClicked:()->Unit)
{
    Column{
        Text(text = "Welcome $name!")
        Button(
            onClick = onStartClicked
        ) {
            Text(text = "Start")
        }
    }
}

@Composable
fun LemonApp() {
    var current by remember { mutableStateOf(1) }
    var squeezeCnt by remember { mutableStateOf(0) }

    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background){
        when(current) {
            1-> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(id = R.string.tap_lemon_tree))
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.lemon_tree),
                        contentDescription = stringResource(id = R.string.lemon_tree),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            current = 2
                            squeezeCnt = (2..4).random()
                        })
                }
            }
            2->{
                LemonTextAndImage(text = R.string.keep_lemonade, painter = R.drawable.lemon_squeeze, descriptor = R.string.lemon, onImageClick = {
                    squeezeCnt--
                    if (squeezeCnt == 0) {current=3}
                })
            }
            3-> {
                LemonTextAndImage(text = R.string.tap_lemonade, painter = R.drawable.lemon_drink, descriptor = R.string.glass_of_lemonade, onImageClick = {
                    current=4
                })
            }
            4-> {
                LemonTextAndImage(text = R.string.tap_empty, painter = R.drawable.lemon_restart, descriptor = R.string.empty_glass, onImageClick = {
                    current=1
                })
            }
        }
    }
}
@Composable
fun LemonTextAndImage(text:Int,painter:Int,descriptor:Int,onImageClick:()->Unit,modifier: Modifier = Modifier)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = text), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = painter), contentDescription = stringResource(id = descriptor),
        modifier = Modifier.wrapContentSize().clickable(onClick = onImageClick).border(BorderStroke(2.dp,
            Color(105,205,216)
        ), shape = RoundedCornerShape(4.dp)).padding(16.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}