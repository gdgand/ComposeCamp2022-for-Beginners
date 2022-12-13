package com.example.android.unscramble.ui

data class GameUiState (
    val currentScrambleWord: String = "",
    val currentWordCount: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false,
    val score: Int = 0,
)