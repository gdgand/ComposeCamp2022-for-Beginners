package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
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
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    // 현재 단계가 몇단계인지 확인
    var step by remember { mutableStateOf(1) }

    // 레몬 짜야하는 횟수
    var lemonCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        // 현재 단계에 따라 다른 이미지와 설명, 동작 수행
        when (step) {
            // 1단계 : 레몬나무에서 레몬 따기
            1 -> {
                // 레몬 나무를 보여주며 레몬을 따달라는 메시지 출력
                LemonadeTextAndImage(
                    explicateResourceId = R.string.lemon_select,
                    painterResourceId = R.drawable.lemon_tree,
                    descriptionResourceId = R.string.lemon_tree_description,
                    onClick = {
                        // 클릭을 할 경우 2단계로 갈 수 있도록 업데이트
                        step = 2
                        // 레몬 짜는 횟수를 랜덤으로 지정(2에서 4사이)
                        lemonCount = (2..4).random()
                    }
                )
            }
            // 2단계 : 레몬 짜기
            2 -> {
                // 레몬을 보여주며 레몬을 짜달라는 메시지 출력
                LemonadeTextAndImage(
                    explicateResourceId = R.string.lemon_squeeze,
                    painterResourceId = R.drawable.lemon_squeeze,
                    descriptionResourceId = R.string.lemon_description,
                    onClick = {
                        // 한번 클릭했을 경우 레몬 짜는 횟수 1차감
                        lemonCount--
                        // 레몬 짜는 횟수가 0이 되었을 경우
                        if (lemonCount == 0) {
                            // 3단계로 넘어갈 수 있도록 업데이트
                            step = 3
                        }
                    }
                )
            }
            // 3단계 : 레모네이드 마시기
            3 -> {
                // 레모네이드를 보여주며 마시라는 메시지 출력
                LemonadeTextAndImage(
                    explicateResourceId = R.string.lemon_drink,
                    painterResourceId = R.drawable.lemon_drink,
                    descriptionResourceId = R.string.lemonade_description,
                    onClick = {
                        // 클릭을 할 경우 4단계로 넘어갈 수 있도록 업데이트
                        step = 4
                    }
                )
            }
            // 4단계 : 빈컵 치우기
            4 -> {
                // 빈컵을 보여주며 다시 한번 시작하겠냐고 물어보는 메시지 출력
                LemonadeTextAndImage(
                    explicateResourceId = R.string.lemon_empty_glass,
                    painterResourceId = R.drawable.lemon_restart,
                    descriptionResourceId = R.string.empty_glass_description,
                    onClick = {
                        // 클릭을 할 경우 1단계로 돌아갈 수 있도록 업데이트
                        step = 1
                    }
                )
            }
        }
    }
}

/**
 * 레모네이드 뷰
 * @param explicateResourceId: 이미지에 따른 설명
 * @param painterResourceId: 보여질 이미지
 * @param descriptionResourceId 이미지 읽어주기
 * @param onClick: 이미지 클릭 이벤트
 * @param modifier: 속성값
 */
@Composable
fun LemonadeTextAndImage(
    explicateResourceId: Int,
    painterResourceId: Int,
    descriptionResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        // 중앙 정렬 및 화면 모두 사용
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // 이미지에 대한 설명(16sp 크기)
        Text(
            text = stringResource(explicateResourceId),
            fontSize = 18.sp
        )
        // 텍스트와 이미지 사이의 공백
        Spacer(modifier = Modifier.height(16.dp))
        // 보여질 이미지
        Image(
            painter = painterResource(painterResourceId),
            contentDescription = stringResource(descriptionResourceId),
            modifier = Modifier
                // 클릭이벤트 할당
                .clickable(
                    onClick = onClick
                )
                // 테두리 보여주기
                .border(
                    // 2dp 굵기를 가지며 색상이 아래와 같은 테두리 선
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    // 끝을 살짝 동그랗게
                    shape = RoundedCornerShape(4.dp)
                )
                // 내부 패딩 16.dp 값을 가짐
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
