package com.yunho.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunho.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                TipTimeApp()
            }
        }
    }
}

@Composable
fun TipTimeApp() {
    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var inputValue by remember { mutableStateOf("") }

        val amount = inputValue.toDoubleOrNull() ?: 0.0
        val tip = calculateTip(amount)

        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

//        val tip = EditNumberField()
        EditNumberField(
            value = inputValue,
            onValueChange = { inputValue = it }
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Composable
//fun EditNumberField(): String {
////    val inputValue = mutableStateOf("0")
//    var inputValue by remember { mutableStateOf("") }
//
//    TextField(
//        modifier = Modifier.fillMaxWidth(),
//        value = inputValue,
//        onValueChange = { inputValue = it }, // value change call back
//        label = { Text(stringResource(R.string.cost_of_service)) }, // place holder
//        singleLine = true,
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//    )
//
//    val amount = inputValue.toDoubleOrNull() ?: 0.0
//    return calculateTip(amount)
//}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
//    val inputValue = mutableStateOf("0")

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange, // value change call back
        label = { Text(stringResource(R.string.cost_of_service)) }, // place holder
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String =
    NumberFormat.getCurrencyInstance().format(tipPercent / 100 * amount)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeApp()
    }
}