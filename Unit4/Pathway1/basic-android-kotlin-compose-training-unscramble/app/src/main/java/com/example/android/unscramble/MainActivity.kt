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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.unscramble.ui.GameViewModel
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
    fun GameStatus(wordCount: Int, score: Int, modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.word_count, wordCount),
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                text = stringResource(id = R.string.score, score),
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun GameLayout(
        modifier: Modifier = Modifier,
        currentScrambledWord: String,
        userGuess: String,
        onKeyboardDone: () -> Unit,
        onUserGuessChanged: (String) -> Unit,
        isGuessWrong: Boolean,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Text(
                text = currentScrambledWord,
                fontSize = 45.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.instructions),
                fontSize = 17.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = userGuess,
                onValueChange = { onUserGuessChanged(it) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    if (isGuessWrong) {
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onKeyboardDone() })
            )
        }
    }

    @Composable
    fun GameScreen(modifier: Modifier = Modifier, gameViewModel: GameViewModel = viewModel()) {
        val gameUiState by gameViewModel.uiState.collectAsState()
        Column(
            modifier = modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameStatus(
                wordCount = gameUiState.currentWordCount,
                score = gameUiState.score
            )
            GameLayout(
                currentScrambledWord = gameUiState.currentScrambledWord,
                onKeyboardDone = { gameViewModel.checkUserGuess() },
                onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
                userGuess = gameViewModel.userGuess,
                isGuessWrong = gameUiState.isGuessedWordWrong
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(
                    onClick = { gameViewModel.skipWord() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.skip))
                }
                Button(
                    onClick = { gameViewModel.checkUserGuess() },
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.submit))
                }
            }
            if (gameUiState.isGameOver){
                FinalScoreDialog(
                    score = gameUiState.score,
                    onPlayAgain = { gameViewModel.resetGame() })
            }
        }
    }

    @Composable
    fun FinalScoreDialog(
        score: Int,
        onPlayAgain: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val activity = (LocalContext.current as Activity)

        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = stringResource(id = R.string.congratulations)) },
            text = { Text(text = stringResource(id = R.string.you_scored, score)) },
            modifier = modifier,
            dismissButton = {
                TextButton(onClick = { activity.finish() }) {
                    Text(text = stringResource(id = R.string.exit))
                }
            },
            confirmButton = {
                TextButton(onClick = { onPlayAgain() }) {
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

