package com.example.tiptime

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun TipTimeScreen() {

    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember {  mutableStateOf( "15.0" ) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipString = calculateTip(amount, tipInput.toDoubleOrNull() ?: 0.0)

    Column(modifier = Modifier.padding(92.dp)
//        .fillMaxSize().wrapContentSize()
        , verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(R.string.calculate_tip)
            , fontSize = 24.sp
            , modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(keyboardOptions = keyboardOptions
                , keyboardAction amountInput)  {
            amountInput = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        EditTipField(tipInput)  {
            tipInput = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(R.string.tip_amount, tipString)
            , fontSize = 20.sp
            , modifier = Modifier.align(Alignment.CenterHorizontally)
            , fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun EditNumberField(keyboardOptions: KeyboardOptions
                    , keyboardActions: KeyboardActions
                    , amountInput: String
                    , onValueChange: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    TextField(value = amountInput
        , onValueChange = onValueChange
        , modifier = Modifier.fillMaxWidth()
        , enabled = true
        , label = { Text(stringResource(R.string.bill_amount))}
        , singleLine = true
//        , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
//                                , imeAction = ImeAction.Next)
        , keyboardOptions = keyboardOptions
        , keyboardActions = keyboardActions
    )
}

@Composable
fun EditTipField(tipInput: String, onValueChange: (String) -> Unit ) {

    TextField(value = tipInput
        , onValueChange = onValueChange
        , modifier = Modifier.fillMaxWidth()
        , enabled = true
        , label = { Text(stringResource(R.string.how_was_the_service))}
        , singleLine = true
        , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
                                    , imeAction = ImeAction.Done
        )
    )
}

internal fun calculateTip(amount: Double
                , tipPercent : Double
                , roundUp : Boolean = true
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}