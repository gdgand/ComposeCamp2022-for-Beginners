package com.compose.camp.hun.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.camp.hun.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat
import java.util.*

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
 * focusManager : compose 내에서 포커스를 제어하기위해 사용.
 */
@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("0") }
    var tipInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 15.0

    val focusManager = LocalFocusManager.current
    var roundUp by remember { mutableStateOf(false) }

    val tip = calculateTip(amount = amount, tipPercent = tipPercent, roundUp)
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
            label = R.string.bill_amount,
            value = amountInput,
            onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            value = tipInput,
            onValueChange = { tipInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyBoardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        RoundTheTipRow(roundUp = roundUp, onRoundUpChange = { roundUp = it })
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChange,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            )
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
 *  keyboardOptions
 *   - ImeAction.Search : 확인버튼이 검색버튼이 된다.
 *   - ImeAction.Send : (확인버튼이) 전송버튼으로 표시
 *   - ImeAction.Go : 대상으로 이동하려고 할 때 사용.
 *
 */
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyBoardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // compose.runtime.getValue, setValue를 import해서 바로 값을 가져오고 대입할 수 있다.
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = label)) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyBoardActions,
    )
}

@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    val tip = (amount * (tipPercent / 100)).let {
        if (roundUp) kotlin.math.ceil(it)
        else it
    }
    return NumberFormat.getCurrencyInstance(Locale.US).format(tip)
}