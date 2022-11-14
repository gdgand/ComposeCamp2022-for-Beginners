package com.compose.camp.hun.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.camp.hun.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

/**
 * Composition - Compose에서 동작하는 구성들?
 * initial composition에서 Composition이 생성되고
 * re composition에서 Composition의 수정되고 업데이트 된다.
 *
 * hoisting (호이스팅) - 끌어 올리기
 * A함수에서 계산되는 변수를 B함수에서 사용해야 할 때, onClick을 고차함수로 A함수에게 전달하면 B함수도 같은 변수를 참조할 수 있다.
 * hoisting을 하는 경우
 *  - 상태(state)를 여러 composable 함수에서 사용하는 경우.
 *  - 앱에서 재사용 할 수 있는 state less composable을 만드는 경우.
 *   * state less composable : remember 변수와 같은 state (상태)를 갖지 않는 Composable 함수.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        TipTimeScreen()
    }
}

/**
 * verticalArrangement : 하위 요소 사이에 8dp 공백이 추가된다.
 */
@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("0") }
    val tip = calculateTip(amountInput.toDoubleOrNull() ?: 0.0)

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * TextField
 *  - value : text field에 표시하는 문자열
 *  - onValueChange : 텍스트 입력할 때 마다 호출되는 함수.
 *
 *  사용자가 텍스트 input에서 값을 입력하고, 이 값이 amountInput값이 바뀌고, 이를 화면에 표시하기위해선
 *  reComposition이 발생되어 UI를 업데이트 해야한다.
 *
 *  reComposition이 발생하기 위해선 'State', 'MutableState'유형의 변수를 사용하여, 해당 변수 값이 변경되면 자동으로 ReComposition된다.
 *
 */
@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    // compose.runtime.getValue, setValue를 import해서 바로 값을 가져오고 대입할 수 있다.
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.cost_of_service)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = amount * (tipPercent / 100)
    return NumberFormat.getCurrencyInstance().format(tip)
}