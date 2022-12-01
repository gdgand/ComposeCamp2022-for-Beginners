package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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


@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize() //메서드들을 꽉채워주어요(아마?)
    .wrapContentSize(Alignment.Center) //구성요소 중앙 배치
){  var result by remember { mutableStateOf(1) } //무슨 일이 일어난거니
    var stringResource= when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else->R.drawable.dice_6
    }
    Column (
        //열이 너비를 기준으로 기기 화면의 중앙에 배치되도록 설정
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = stringResource),
            contentDescription = result.toString()) //이미지 가져오기(+콘텐츠설명. 안드로이드 개발에 중요한 부분이랍디다)
        Spacer(modifier = Modifier.height(16.dp)) //공백 만들기
        Button(onClick = { result=(1..6).random()}) {
                Text(text=stringResource(id = R.string.roll), fontSize = 24.sp)
            }
    }
}

@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage()
}