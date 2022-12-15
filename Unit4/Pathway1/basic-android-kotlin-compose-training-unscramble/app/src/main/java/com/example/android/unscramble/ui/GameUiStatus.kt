package com.example.android.unscramble.ui

data class GameUiStatus(
    val currentScrambledWord: String = "",
    val isGuessWordWrong: Boolean = false,
    val score: Int = 0,
)

