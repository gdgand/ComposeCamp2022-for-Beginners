/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.happybirthday

import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BirthdayGreetingWithImage(
                        message = getString(R.string.happy_birthday_text),
                        from = getString(R.string.signature_text)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        // Greeting("james")
        // BirthdayGreetingWithText(message = "Happy Birthday Sam!", from = "- from Emma")
        BirthdayGreetingWithImage(message = "Happy Birthday Sam!", from = "- from Emma")
    }
}

@Composable
fun BirthdayGreetingWithImage(message: String, from: String) {

    val image = painterResource(R.drawable.androidparty)

    Box {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = message, from = from)
    }

}

@Composable
fun BirthdayGreetingWithText(message: String, from: String) {
    // Row {
    Column {
        Text(
            text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                //.wrapContentWidth(Alignment.Start)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                //.wrapContentWidth(Alignment.End)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}



/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("hhyeok")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
*/





