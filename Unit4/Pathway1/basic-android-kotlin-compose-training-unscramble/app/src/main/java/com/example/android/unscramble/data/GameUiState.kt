package com.example.android.unscramble.data

data class GameUiState(
    var curScrambledWord: String = "",
    var curWordCnt: Int = 0,
    var score: Int = 0,
    var isGuessedWordWrong: Boolean = false,
    var isGameOver: Boolean = false
)