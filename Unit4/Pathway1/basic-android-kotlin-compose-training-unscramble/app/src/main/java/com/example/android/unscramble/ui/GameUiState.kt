package com.example.android.unscramble.ui
//상태 UI의 모델 클래스
data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount:Int =0,
    val isGuessedWordWrong: Boolean = false,
    val score:Int=0,
    val isGameOver: Boolean = false
)