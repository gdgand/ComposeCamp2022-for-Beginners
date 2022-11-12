package com.kakao.dale.band.mydiceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
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
import com.kakao.dale.band.mydiceroller.ui.theme.MyDiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

/**
 * Modifier를 매개변수로 전달하게되면 ColumnScope/RowScope 내에서만 사용할 수 있는 속성을 전달할 수 있다.
 * 또한, 외부에서 속성을 전달하기 때문에, 유연한 설정도 가능하다. (A함수에선 a설정, B함수에선 b설정)
 * ReComposition이 발생할 때마다 Modifier를 생성하지 않을 수 있어서 효율적이다..?
 *
 * remember 객체.
 * @Composable을 붙인 함수는 stateLess 상태.(특정 상태를 가지지않고, 호출 할 때 바로 적용되는 방식)
 * 그래서 result에 값이 저장되지 않음..? 아무래도 reComposition이 될 때 마다 state값이 없기 때문에, 1로 초기화 될 것이다.
 * 이를 해결하기위해 remember Composable을 사용하여 객체를 메모리에 저장할 수 있다.
 */
@Preview(showBackground = true)
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    @DrawableRes
    val imageResource: Int = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.ic_launcher_foreground
    }

    // Alignment.CenterHorizontally : 열이 너비를 기준으로 화면 중앙에 배치된다.
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(text = stringResource(id = R.string.roll))
        }
    }
}