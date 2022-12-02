package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun LemonApp() {
  var currentStep by remember { mutableStateOf(1) }
  var squeezeCount by remember { mutableStateOf(0) }

  when (currentStep) {
    1 -> LemonTextAndImage(R.string.lemon_tree_content_description, R.drawable.lemon_tree) {
      currentStep = 2
      squeezeCount = (2..4).random()
    }
    2 -> LemonTextAndImage(R.string.lemon_content_description, R.drawable.lemon_squeeze) {
      squeezeCount -= 1

      if (squeezeCount == 0) {
        currentStep = 3
      }
    }
    3 -> LemonTextAndImage(R.string.glass_of_lemonade_content_description, R.drawable.lemon_drink) {
      currentStep = 4
    }
    4 -> LemonTextAndImage(R.string.empty_glass_content_description, R.drawable.lemon_restart) {
      currentStep = 1
    }
  }
}

@Composable
fun LemonTextAndImage(stringId: Int, painterId: Int, onClicked: () -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(text = stringResource(stringId), fontSize = 18.sp)
    Spacer(modifier = Modifier.height(16.dp))
    Image(
      painter = painterResource(painterId),
      contentDescription = stringResource(stringId),
      modifier = Modifier
        .wrapContentSize()
        .border(width = 2.dp, color = Color(105, 205, 216), shape = RoundedCornerShape(4.dp))
        .clickable {
          onClicked()
        }
    )
  }
}