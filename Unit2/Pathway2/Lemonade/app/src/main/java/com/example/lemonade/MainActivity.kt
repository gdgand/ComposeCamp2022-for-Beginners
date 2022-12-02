package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemontree()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lemontree() {
    var current by remember { mutableStateOf(1) }

    val currentText = when(current){
        1 -> R.string.Lemontree
        2 -> R.string.Lemon
        3 -> R.string.Lemonade
        else -> R.string.Emptyglass
    }

    val currentImage = when(current){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(currentText))
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(currentImage),
            contentDescription = currentImage.toString(),
            modifier = Modifier.clickable {
                when(current){
                    4 -> current = 1
                    else -> current += 1
                }
            }
        )
    }
}
