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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Board()
                }
            }
        }
    }
}

@Composable
fun Board() {
    var position by remember { mutableStateOf(0) }
    var squeezeCount by remember { mutableStateOf(2) }

    val content = when(position) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon
        2 -> R.string.lemonade
        else -> R.string.empty_glass
    }

    val onImageClick: () -> Unit = {
        when(position) {
            0 -> {
                squeezeCount = (2..4).random()
                position++
            }

            1 -> {
                squeezeCount--
                if (squeezeCount <= 0) {
                    position++
                }
            }

            else -> position = (position + 1) % 4
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(content), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Card(position, onImageClick)
    }
}

@Composable
fun Card(position: Int,
         onImageClick: () -> Unit) {
    val imageResource = when(position) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val desc = when(position) {
        0 -> R.string.lemon_tree_desc
        1 -> R.string.lemon_desc
        2 -> R.string.lemonade_desc
        else -> R.string.empty_glass_desc
    }

    Image(
        painter = painterResource(imageResource),
        contentDescription = stringResource(desc),
        modifier = Modifier
            .wrapContentSize()
            .border(
                BorderStroke(2.dp, colorResource(R.color.border)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onImageClick)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Board()
    }
}