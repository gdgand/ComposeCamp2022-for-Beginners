package com.example.android.unscramble.ui

data class GameUIState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val isGameOver: Boolean = false
)
