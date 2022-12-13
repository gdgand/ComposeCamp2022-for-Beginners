package com.example.android.unscramble.ui

data class GameUiState (
    val currentScrambleWord: String = "",
    val isGuessedWordWrong: Boolean = false,
)