package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    TipTimeScreen()
                }
            }
        }
    }
}
@Composable
fun TipTimeScreen(){
    val focusManager = LocalFocusManager.current //포커스제어..포커스 이동 및 지우기 등등
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember{ mutableStateOf(false)} //반올림 상태 저장

    val amount = amountInput.toDoubleOrNull() ?: 0.0 //금액. null입력상태인 경우 0으로 설정
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = caculateTip(amount, tipPercent, roundUp) //팁 계산값 반환하여 저장

    Column(modifier = Modifier.padding(32.dp),
        //하위요소에 고정된 공백 추가
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text( //제목!
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        //공백추가
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField( //입력창 함수 불러오기..
            value = amountInput,
            onValueChange = {amountInput=it},
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            value = "",
            onValueChange ={ tipInput=it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.clearFocus()}
            )
        )
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = {roundUp=it})
        Spacer(modifier = Modifier.height(24.dp))
        Text( //총 계산결과: 를 표기하는 텍스트
            text = stringResource(id = R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            )
    }
}

@Composable
fun EditNumberField( //입력창
    @StringRes label:Int, //문자열 리ㅏ소스 참조 어노테이션
    value :String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onValueChange:(String)->Unit, //상태 호이스팅을 위한 매개변수 추가
  //  modifier: Modifier=Modifier
){
    //상태추적인식할 수 있도록 유형변경  어머 어려워
//    var amountInput by remember { mutableStateOf("") }
//    val amount = amountInput.toDoubleOrNull()?:0.0 //String Double로 변환(null이면 0반환)
//    val tip= caculateTip(amount) //팁

    TextField(value = value, //전달하는 문자열 값을 표기
        onValueChange =onValueChange, //사용자가 텍스트를 입력할 때 트리거되는 람다 콜백
        label = { Text(stringResource(label))}, //텍스트 입력창 라벨
        modifier = Modifier.fillMaxWidth(),
        singleLine = true, //여러 줄에서 가로로 스크롤 가능한 하나의 줄로 압축
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ), //키패드타입.
        keyboardActions = keyboardActions
    )
}
private fun caculateTip(//계산하기
    amount: Double, //서비스 비용
    tipPercent: Double=15.0, //팁 비율 (기본: 15퍼)
    roundUp: Boolean //반올림여부 알아내서 계산하세용,
): String {
    var tip=tipPercent/100 * amount //총 팁 계산
    if(roundUp)
        tip= kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip) //팁을 통화 형식으로 지정하고 반환(숫자형식지정클래스)
}

@Composable
fun RoundTheTipRow( //반올림해주는 함수에요..
    modifier: Modifier=Modifier,
    roundUp: Boolean,
    onRoundUpChanged:(Boolean)->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment =  Alignment.CenterVertically
    ){
        Text(stringResource(id = R.string.round_up_tip))
        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange =onRoundUpChanged,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}