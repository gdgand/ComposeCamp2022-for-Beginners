package com.example.tiptime

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusDirection.Companion.Down
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
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
    var tipInput by remember {  mutableStateOf( "" ) }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipString = calculateTip(amount, tipInput.toDoubleOrNull() ?: 0.0, roundUp)

    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.padding(32.dp)
//        .fillMaxSize().wrapContentSize()
        , verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(R.string.calculate_tip)
            , fontSize = 24.sp
            , modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))


        EditNumberField( R.string.bill_amount
                , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
                                                    , imeAction = ImeAction.Next)
                , keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down) })
                , value = amountInput
                , onValueChange = { amountInput = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField( R.string.how_was_the_service
                , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
                                                    , imeAction = ImeAction.Done)
                , keyboardActions = KeyboardActions(onDone = {
                          focusManager.clearFocus() })
                , value = tipInput
                , onValueChange = { tipInput = it }
        )

        Spacer(modifier = Modifier.height(24.dp))
        RoundTheTipRow(roundUp = roundUp) {
            roundUp = it
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
fun EditNumberField(@StringRes label: Int
                    , keyboardOptions: KeyboardOptions
                    , keyboardActions: KeyboardActions
                    , modifier: Modifier = Modifier
                    , value: String
                    , onValueChange: (String) -> Unit
) {
    TextField(value = value
        , onValueChange = onValueChange
        , modifier = modifier.fillMaxWidth()
        , enabled = true
        , label = { Text(stringResource(label)) }
        , singleLine = true
        , keyboardOptions = keyboardOptions
        , keyboardActions = keyboardActions
    )
}

@VisibleForTesting
internal fun calculateTip(amount: Double
                , tipPercent : Double
                , roundUp : Boolean
): String {
    var tip = (tipPercent / 100.0) * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun RoundTheTipRow(roundUp: Boolean
                   , modifier: Modifier = Modifier
                   , onCheckedChange: (Boolean)->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .wrapContentWidth()
            .size(48.dp)
        , verticalAlignment = Alignment.CenterVertically
//        , horizontalArrangement = Arrangement.Center

    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
//        Spacer(modifier = Modifier.width(24.dp))
        Switch(checked = roundUp, onCheckedChange = onCheckedChange
            ,  modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
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