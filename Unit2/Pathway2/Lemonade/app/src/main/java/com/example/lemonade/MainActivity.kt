package com.example.lemonade

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun HTMLemonadeandImage(textId : Int, imageId : Int, onImageClick: () -> Unit) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = textId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = imageId), contentDescription = "1")
    }
}
@Composable
fun LemonadeApp() {
    var lemonade_step by remember { mutableStateOf(1) }
    var squeeze_cnt by remember { mutableStateOf(0) }

    when (lemonade_step) {
        1 -> {
            HTMLemonadeandImage(textId = R.string.first_step, imageId = R.drawable.lemon_tree,
            onImageClick = {lemonade_step = 2
            squeeze_cnt = (2..4).random()})
        }


    }
}
@Preview(showBackground = true)
@Composable
fun LemonadePriview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

