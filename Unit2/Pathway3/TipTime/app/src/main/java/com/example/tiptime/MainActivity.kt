package com.example.tiptime

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Down
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat
import kotlin.math.round

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
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val tipPercent = tipInput.toDoubleOrNull() ?:0.0
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount,tipPercent,roundUp)

    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.padding(32.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp) /*하위 요소 고정된 8dp가 추가되는 부분*/) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(label = R.string.cost_of_service,value = amountInput, onValueChange = {amountInput = it}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next), keyboardAction = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down)}))
        EditNumberField(label = R.string.tip_amount,value = tipInput,onValueChange = {tipInput = it},keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done), keyboardAction = KeyboardActions(onDone = {focusManager.clearFocus()})
        )
        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged = {roundUp=it})
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditNumberField(label:Int,value:String, onValueChange:(String)->Unit, keyboardOptions:KeyboardOptions,keyboardAction:KeyboardActions){
    TextField(value = value, onValueChange = onValueChange,
    label = { Text(text = stringResource(id = label))},
    modifier = Modifier.fillMaxWidth(),
    singleLine = true,
    keyboardOptions = keyboardOptions
    )
}

@Composable
fun RoundTheTipRow(roundUp:Boolean, onRoundUpChanged:(Boolean)->Unit, modifier: Modifier = Modifier)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text (text = stringResource(id = R.string.round_up_tip))
        Switch(checked = roundUp, onCheckedChange = onRoundUpChanged, modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
        colors = SwitchDefaults.colors (uncheckedThumbColor = Color.DarkGray))
    }
}
private fun calculateTip (amount:Double, tipPercent:Double = 15.0,roundUp: Boolean) : String {
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
       TipTimeScreen()
    }
}