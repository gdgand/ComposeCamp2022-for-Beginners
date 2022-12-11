package com.example.android.unscramble.ui.theme

data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 0,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)