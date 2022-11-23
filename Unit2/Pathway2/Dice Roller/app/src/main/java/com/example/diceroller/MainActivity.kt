package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}
@Preview(showBackground = true)
@Composable
fun DiceWithButtonAndImage(modifier: Modifier= Modifier
    .fillMaxSize()//DiceWithButtonAndImage가 전체 화면을 꽉 채우도록한다
    .wrapContentSize(Alignment.Center)//구성요소들이 세로와 가로로 모두 중앙 배치되도록한다
) {
    var result by remember{ mutableStateOf(1) }
    val imageResource=when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else->R.drawable.dice_6
    }
    Column(
        modifier=modifier,//이 Column이 매개변수의 modifier를 modifier로 쓰도록 설정함
        horizontalAlignment = Alignment.CenterHorizontally//너비 기준으로 기기 화면의 중앙에 배치
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result=(1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}
