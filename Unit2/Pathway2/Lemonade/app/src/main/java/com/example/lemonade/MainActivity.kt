package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
fun LemonApp() {
    val lemonData = arrayOf(
        LemonData(R.string.stage1, R.string.lemon_tree_desc, R.drawable.lemon_tree),
        LemonData(R.string.stage2, R.string.lemon_desc, R.drawable.lemon_squeeze),
        LemonData(R.string.stage3, R.string.glass_of_lemonade_desc, R.drawable.lemon_drink),
        LemonData(R.string.stage4, R.string.empty_glass_desc, R.drawable.lemon_restart),
    )

    var currentStage by remember { mutableStateOf(0) }
    var squeezeCount by remember { mutableStateOf(0) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(lemonData[currentStage].titleResourceId),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(lemonData[currentStage].imageResourceId),
                contentDescription = stringResource(lemonData[currentStage].descResourceId),
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color(105, 205, 216)), RoundedCornerShape(8.dp))
                    .wrapContentSize()
                    .clickable(
                        onClick = {
                            if (currentStage == 0) {
                                currentStage++
                                squeezeCount = (2..4).random()
                                return@clickable

                            } else if (currentStage == 1) {
                                squeezeCount--

                                if (squeezeCount <= 0) {
                                    currentStage++
                                }

                                return@clickable

                            } else if (currentStage == lemonData.size-1) {
                                currentStage = 0
                                return@clickable
                            }

                            currentStage++
                        }
                    )
            )
        }
    }
}


@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonApp()
    }
}

data class LemonData(val titleResourceId: Int, val descResourceId: Int, val imageResourceId: Int)