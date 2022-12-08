package com.example.hellocity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellocity.ui.theme.HelloCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloCityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HelloCityApp()
                }
            }
        }
    }
}


//@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {

    Column(modifier = Modifier.padding(16.dp)
        .fillMaxSize().wrapContentSize()
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(value = name
            , onValueChange = onNameChange
            , label = { Text("Name") }
            , singleLine = true
//            , keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
//            , keyboardActions = KeyboardActions(onDone = {
//                submit()
//                keyboardController?.hide() })
        )
    }
}



@Composable
fun HelloCityApp() {
    var name by remember { mutableStateOf("") }

    HelloContent(name) { name = it }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloCityTheme {
        HelloCityApp()
    }
}