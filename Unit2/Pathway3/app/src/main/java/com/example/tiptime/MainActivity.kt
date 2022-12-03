package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat

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
    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = caculateTip(amount)

    Column(modifier = Modifier.padding(32.dp),
        //하위요소에 고정된 공백 추가
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        //공백추가
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(
            value = amountInput,
            onValueChange = {amountInput=it}
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text( //총 계산결과: 를 표기하는 텍스트
            text = stringResource(id = R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
            )
    }

}

@Composable

fun EditNumberField(
    value :String,
    onValueChange:(String)->Unit //상태 호이스팅을 위한 매개변수 추가
){
    //상태추적인식할 수 있도록 유형변경  어머 어려워
//    var amountInput by remember { mutableStateOf("") }
//    val amount = amountInput.toDoubleOrNull()?:0.0 //String Double로 변환(null이면 0반환)
//    val tip= caculateTip(amount) //팁
    TextField(value = value, //전달하는 문자열 값을 표기
        onValueChange =onValueChange, //사용자가 텍스트를 입력할 때 트리거되는 람다 콜백
        label = { Text(stringResource(id = R.string.cost_of_service))}, //텍스트 입력창 라벨
        modifier = Modifier.fillMaxWidth(),
        singleLine = true, //여러 줄에서 가로로 스크롤 가능한 하나의 줄로 압축
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) //키패드타입.
    )
}
private fun caculateTip(
    amount: Double, //서비스 비용
    tipPercent: Double=15.0 //팁 비율 (기본: 15퍼)
): String {
    val tip=tipPercent/100 * amount //총 팁 계산
    return NumberFormat.getCurrencyInstance().format(tip) //숫자를 통화 형식으로 지정(숫자형식지정클래스)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}