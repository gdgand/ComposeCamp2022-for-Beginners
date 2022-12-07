/*
 * Copyright (c)2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.unscramble

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.unscramble.ui.theme.UnscrambleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnscrambleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    GameScreen()
                }
            }
        }
    }

    @Composable
    fun GameStatus(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.word_count, 0),
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                text = stringResource(id = R.string.score, 0),
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun GameLayout(modifier: Modifier = Modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Text(
                text = "",
                fontSize = 45.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.instructions),
                fontSize = 17.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.enter_your_word)) },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {})
            )
        }
    }

    @Composable
    fun GameScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameStatus()
            GameLayout()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.skip))
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.submit))
                }
            }
        }
    }

    @Composable
    fun FinalScoreDialog(
        onPlayAgain: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val activity = (LocalContext.current as Activity)

        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = stringResource(id = R.string.congratulations)) },
            text = { Text(text = stringResource(id = R.string.you_scored, 0)) },
            modifier = modifier,
            dismissButton = {
                TextButton(onClick = { activity.finish() }) {
                    Text(text = stringResource(id = R.string.exit))
                }
            },
            confirmButton = {
                TextButton(onClick = { onPlayAgain }) {
                    Text(text = stringResource(id = R.string.play_again))
                }
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun UnscramblePreview() {
        UnscrambleTheme {
            GameScreen()
        }
    }

}

