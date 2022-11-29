package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.graphics.Color
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
                LemonadeApp()
            }
        }
    }

    @Composable
    fun LemonadeApp() {
        var step by remember {
            mutableStateOf(1)
        }

        var squeezeCnt by remember {
            mutableStateOf(0)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            when (step) {
                1 -> {
                    Lemonade(
                        stringId = R.string.lemon_text_01,
                        imgId = R.drawable.lemon_tree,
                        onImgClick = {
                            step = 2
                            squeezeCnt = (2..4).random()
                        }
                    )
                }
                2 -> {
                    Lemonade(
                        stringId = R.string.lemon_text_02,
                        imgId = R.drawable.lemon_squeeze,
                        onImgClick = {
                            squeezeCnt -= 1
                            if (squeezeCnt < 1) {
                                step = 3
                            }
                        }
                    )
                }
                3 -> {
                    Lemonade(
                        stringId = R.string.lemon_text_03,
                        imgId = R.drawable.lemon_drink,
                        onImgClick = {
                            step = 4
                        }
                    )
                }
                4 -> {
                    Lemonade(
                        stringId = R.string.lemon_text_04,
                        imgId = R.drawable.lemon_restart,
                        onImgClick = {
                            step = 1
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun Lemonade(
        modifier: Modifier = Modifier,
        stringId: Int,
        imgId: Int,
        onImgClick: () -> Unit,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = stringId), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                modifier = Modifier
                    .border(
                        2.dp,
                        color = Color(105, 205, 216),
                        shape = RoundedCornerShape(4)
                    ).padding(16.dp)
                    .clickable(
                        onClick = onImgClick
                    ),
                painter = painterResource(imgId),
                contentDescription = stringResource(id = stringId),
            )

        }
    }


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        LemonadeTheme {
            LemonadeApp()
        }
    }
}