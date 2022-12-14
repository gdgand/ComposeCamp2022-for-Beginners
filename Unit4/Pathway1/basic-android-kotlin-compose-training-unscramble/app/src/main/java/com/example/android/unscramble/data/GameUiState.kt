package com.example.android.unscramble.data

data class GameUiState(
    val curScrambledWord: String = "",
    val curWordCnt: Int = 0,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)