package com.example.lemonade

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Composable
fun DirectionOne(title: Int
                 , image: Int
                 , contDesc: Int
                 , onClickAction: ()->Unit
) {
    Surface(modifier = Modifier.fillMaxSize()
        , color = MaterialTheme.colors.background
    ){


        Column(modifier = Modifier.fillMaxSize().wrapContentSize()
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(title)
                , fontSize = 24.sp
                , fontStyle = FontStyle.Italic
                , modifier = Modifier.padding(8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
//                    .border(BorderStroke(2.dp,
//                        color = Color(105, 205, 216))
//                        , shape = RoundedCornerShape(4.dp))
//                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(painterResource(image)
                , contentDescription = stringResource(contDesc)
                , modifier = Modifier.wrapContentSize()
                    .clickable(enabled = true
    //                , onClickLabel: String? = null
    //                , role: Role? = null
                      , onClick = onClickAction)
                    .border(
                        BorderStroke(2.dp, Color(105, 205, 216))
                        , shape = RoundedCornerShape(4.dp))
            )
        }
    }

}


@Composable
fun LemonadeApp() {
    var currentState by remember { mutableStateOf(1)}
    var maxSqueezeTap: Int = (2..4).random()
    var currentSqueezeTap by remember { mutableStateOf(0) }

    var image = when (currentState) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var title = when (currentState) {
        1 -> R.string.direction_1
        2 -> R.string.direction_2
        3 -> R.string.direction_3
        else -> R.string.direction_4
    }

    var contDesc = when (currentState) {
        1 -> R.string.cont_desc_1
        2 -> R.string.cont_desc_2
        3 -> R.string.cont_desc_3
        else -> R.string.cont_desc_4
    }

    WelcomeScreen("Cuttie!!") {

    }

    DirectionOne(title, image, contDesc) {

        when (currentState) {
            1 -> { currentSqueezeTap = 0 }
            2 -> {  // fill "squeeze routine" here
                if (currentSqueezeTap < maxSqueezeTap) {
                    currentSqueezeTap++
                    return@DirectionOne
                }
            }
            else -> { }
        }


        if (currentState in 1..4) { }
        else {
            currentState %= 4
        }
        currentState++
    }
}


@Composable
fun WelcomeScreen(name: String, onStartClicked: () -> Unit) {
    Column {
        Text(text = "Welcome $name!")
//        Button(onClick = onStartClicked) {
//            Text("Start")
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}