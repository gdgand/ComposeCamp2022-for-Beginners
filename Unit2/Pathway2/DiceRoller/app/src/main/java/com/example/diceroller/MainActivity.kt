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
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            // 해당 레이아웃이 전체를 차지할 수 있도록 maxSize 지정
            .fillMaxSize()
            // 중앙 정렬을 시켜준다.
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    /*
     * 주사위 눈금
     * composable 은 기본적으로 상태를 저장하지 않는 스테이트리스(Stateless)이다.
     * 하지만 remember 컴포저블을 사용하여 메모리에 객체를 저장할 수 있다.
     * result 접근자 프로퍼티 권한을 remember 에게 위임한다.
     *  - remember : 생성된 값을 기억하고 반환
     *  - mutableStateOf(1) : 해당 클래스는 Compose 에서 읽기 및 쓰기를 관찰하는 옵저버 반환
     */
    var result by remember { mutableStateOf(1) }

    // 사용할 이미지 가져오기
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        // 전체 중앙정렬
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 주사위 이미지
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )
        /*
         * 서로간의 간격 조정
         * Spacer - Modifier 객체를 매개변수로 사용
         */
        Spacer(modifier = Modifier.height(16.dp))
        // 굴리기 버튼
        Button(
            // 람다식
            onClick = {
                // 1~6사이의 랜덤한 숫자
                result = (1..6).random()
            }
        ) {
            Text(text = stringResource(id = R.string.roll))
        }
    }
}
