package com.example.calculatetip

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
import com.example.calculatetip.ui.theme.CalculateTipTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTipTheme {
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
fun TipTimeScreen() {
    /*
     * 계산 금액
     * mutableStateOf 사용하여 관찰가능한 옵저버 객체로 반환 받아 사용
     * remember 객체로 위임하여 메모리에 값을 저장하여 바뀐 값을 반환받아 사용가능
     */
    var amountInput by remember { mutableStateOf("0") }
    // 할인율
    var tipInput by remember { mutableStateOf("") }
    // 반올림 할지 말지에 대한 값
    var roundUp by remember { mutableStateOf(false) }

    // 기본 금액(입력받은 amountInput 을 double 로 캐스팅, null 이라면 0.0 세팅)
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    // 할인율
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    // 할인된 금액 반환
    val tip = calculateTip(amount, tipPercent, roundUp)

    // 컴포즈에서 포커스를 제어하는 객체
    val focusManager = LocalFocusManager.current

    Column(
        // 패딩 값 32dp
        modifier = Modifier.padding(32.dp),
        // Column 내부의 자식들을 해당 수치만큼 떨어뜨림
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 타이틀
        Text(
            // 실제 텍스트 세팅
            text = stringResource(id = R.string.calculate_tip),
            // 폰트 크기 24sp
            fontSize = 24.sp,
            // 텍스트 중앙정렬
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        // 공간 띄우기
        Spacer(modifier = Modifier.height(16.dp))
        // 할인전 가격 입력 텍스트
        EditNumberField(
            label = R.string.bill_amount,
            /*
             * TextField 에 대한 키보드 옵션 지정
             *  - 입력타입은 숫자 타입
             *  - 다음 버튼 추가
             */
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            // 키보드 동작 지정 - 다음 텍스트 필드로 이동
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            // 현재 텍스트를 넘겨주고
            value = amountInput,
            // onValueChange 를 받아 값이 바뀔경우 저장
            onValueChange = { amountInput = it }
        )
        // 할인율 입력 텍스트
        EditNumberField(
            label = R.string.how_was_the_service,
            // 다음 버튼 대신 종료 버튼 추가
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            // 포커스 지우기 - 키보드 닫힘
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            value = tipInput,
            onValueChange = { tipInput = it }
        )
        // 반올림 여부 스위치
        RoundTheTipRow(
            // 상태 적용
            roundUp = roundUp,
            // 클릭 되었을 경우 값 반영
            onRoundUpChanged = { roundUp = it }
        )
        // 공간 띄우기
        Spacer(Modifier.height(24.dp))
        // 할인된 금액
        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * 입력 텍스트 필드
 * @param label 어떤 값을 입력하는 입력 필드인지 표시해주는 라벨
 * @param value 표시할 현재 값
 * @param keyboardOptions 키보드 옵션
 * @param keyboardActions 키보드 동작
 * @param onValueChange 텍스트에 변경이 있을 경우 업데이트 할 수 있도록 트리거 시켜주는 콜백 람다
 * @param modifier 재사용성을 높이기 위한 Modifier 객체
 * label 값이 문자열 리소스를 참조하는 것을 안내
 * 상태 호이스팅을 사용하여 상단으로 변경된 값을 넘겨줌
 */
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // 사용자 입력 텍스트 필드
    TextField(
        // 가로 길이 모두를 사용하겠다
        modifier = modifier.fillMaxWidth(),
        // 한줄만 사용
        singleLine = true,
        // 키보드 옵션 적용
        keyboardOptions = keyboardOptions,
        // 키보드 동작 적용
        keyboardActions = keyboardActions,
        // amountInput 값을 세팅
        value = value,
        // 입력받은 값이 바뀌면 해당 값을 amountInput 값에 넣어줌
        onValueChange = onValueChange,
        // 상단에 보여질 라벨
        label = { Text(text = stringResource(id = label)) }
    )
}

/**
 * 팁 반올림 설정
 * @param roundUp 선택여부
 * @param onRoundUpChanged 클릭 될경우의 콜백
 * @param modifier 속성
 * 상태 호이스팅 사용
 */
@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // 가로
    Row(
        // 가로 모두를 사용하며 크기 48 dp, 중앙정렬
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 반올림 설정 선택 설명
        Text(text = stringResource(R.string.round_up_tip))
        // 선택을 위한 스위치
        Switch(
            // 가로 끝으로 배치
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            // 선택 여부
            checked = roundUp,
            // 클릭 될 경우 콜백
            onCheckedChange = onRoundUpChanged,
            // 선택전 색상을 진하게 하여 가시성 높임
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            )
        )
    }
}

/**
 * 할인율 계산 로직
 * @param amount 기본 금액
 * @param tipPercent 할인율
 * @param roundUp 반올림 여부
 * @return 할인율이 적용된 금액
 */
private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    // 할인이 적용된 금액
    var tip = tipPercent / 100 * amount

    // 반올림을 원한다면 정수로 반올림
    if (roundUp)
        tip = kotlin.math.ceil(tip)

    // 단순한 숫자가 아니라 금액 형식으로 포맷 하여 반환
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculateTipTheme {
        TipTimeScreen()
    }
}
