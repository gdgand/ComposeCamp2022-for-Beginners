package com.example.tiptime

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
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

@Composable
fun TipTimeScreen(){

    var roundUp by remember { mutableStateOf(false) }//스위치 상태
    var amountInput by remember { mutableStateOf("") }//입력창에 표시될 값
    val amount=amountInput.toDoubleOrNull() ?: 0.0//amountInput의 값을 Double혹은 null형으로 변환
    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip= calculateTip(amount, tipPercent, roundUp)//amount값을 calculateTip에 넣어 내야할 팁값을 계산한다
    val focusManager = LocalFocusManager.current

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
            label=R.string.bill_amount,
            keyboardOptions=KeyboardOptions(
              keyboardType=KeyboardType.Number,
              imeAction=ImeAction.Next
            ),
            keyboardActions=KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            value=amountInput,
            onValueChange = { amountInput=it }
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            keyboardOptions=KeyboardOptions(
                keyboardType=KeyboardType.Number,
                imeAction=ImeAction.Done
            ),
            keyboardActions=KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            value = tipInput,
            onValueChange ={ tipInput=it }
        )
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundChanged = {roundUp=it}//roundUp값을 업데이트
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(//이곳에 EditNumberField의 tip이라는 변수값이 적혀져야 하는데 그럴수없다 -> 상태 호이스팅 한다
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}
@Composable
fun EditNumberField(//숫자를 입력하는 TextField를 만들때 재사용가능함
    @StringRes label: Int,//TextField별로 다른 라벨이 필요함 재사용을 하려면
    keyboardOptions: KeyboardOptions,//각 텍스트 필드별 키보드 옵션다르게 함
    keyboardActions: KeyboardActions,
    value: String, //호이스팅 된 값을 받아옴, 텍스트필드에 표시될 값
    onValueChange: (String)->Unit, //호이스팅 된 값 업데이트하는 콜백함수
    modifier: Modifier=Modifier //컴포저블 함수에 Modifier를 주면 재사용성이 높아짐
){
    TextField(
        value = value,//입력창에 표시될 값
        onValueChange =onValueChange,
        label={ Text(stringResource(id = label))},//라벨에 표시될것
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundChanged: (Boolean)->Unit,
    modifier: Modifier=Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            modifier= modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,//스위치가 선택되어 있는지 여부, 스위치 컴포저블 상태
            onCheckedChange =onRoundChanged, //스위치를 클릭할때 호출되는 콜백, it으로 변경된 스위치의 상태가 들어옴
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color(190, 79, 79, 255)
            )
        )
    }
}

private fun calculateTip(
    amount:Double,//낸 돈
    tipPercent: Double=15.0,//기본 팁값 15%
    roundUp: Boolean//팁값 반올림 할지 말지
) : String {//이 함수의 반환은 String
    var tip = tipPercent / 100 * amount //팁값
    if(roundUp)//roundUp이 true면 반올림해서 tip을 업데이트
        tip=kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}