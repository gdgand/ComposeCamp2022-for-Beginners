package com.example.android.unscramble.ui

data class GameUiStatus(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 0,
    val isGuessWordWrong: Boolean = false,
    val score: Int = 0,
)

