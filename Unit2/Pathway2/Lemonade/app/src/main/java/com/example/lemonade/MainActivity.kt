package com.example.lemonade

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.ui.theme.SkyBlue

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
fun LemonadeApp() {
    // 솔루션 코드
    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(currentStep){
            1 -> {
                LemonadeTextAndImage(
                    textLabelResourceId = R.string.lemon_tree_desc,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_title,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                LemonadeTextAndImage(
                    textLabelResourceId = R.string.lemon_desc,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_title,
                    onImageClick = {
                        if(--squeezeCount == 0){
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                LemonadeTextAndImage(
                    textLabelResourceId = R.string.lemonade_desc,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.lemonade_title,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                LemonadeTextAndImage(
                    textLabelResourceId = R.string.glass_desc,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.glass_title,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }

    // Dice Roller처럼 아래와 같이 시도했으나..
    // 솔루션의 코드가 더 깔끔해서 솔루션 코드로 변경했다.
/*
    var result by remember { mutableStateOf(1) }

    var imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var textResource = when(result) {
        1 -> R.string.lemon_tree_desc
        2 -> R.string.lemon_desc
        3 -> R.string.lemonade_desc
        else -> R.string.glass_desc
    }

    var descResource = when(result) {
        1 -> R.string.lemon_tree_title
        2 -> R.string.lemon_title
        3 -> R.string.lemonade_title
        else -> R.string.glass_title
    }
*/

}

@Composable
fun LemonadeTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview(){
    LemonadeTheme {
        LemonadeApp()
    }
}