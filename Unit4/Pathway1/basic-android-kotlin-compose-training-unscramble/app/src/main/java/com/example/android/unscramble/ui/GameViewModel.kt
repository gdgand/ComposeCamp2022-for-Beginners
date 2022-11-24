package com.example.android.unscramble.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "GameViewModel"

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()
    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun getAnswer()  = currentWord

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(it: String) {
        userGuess = it
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // 점수 더하고, 다음 스테이지
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update {
                it.copy(isUserGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
//        Log.d(TAG, "usedWords.size = ${usedWords.size}")
        if (_uiState.value.numberOfStage <= usedWords.size) {
            _uiState.update { currentState ->
                currentState.copy(isUserGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true)
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    isUserGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }
    fun skipWord() {
        _uiState.update { currentState ->
            currentState.copy(isUserGuessedWordWrong = false
                , currentScrambledWord = pickRandomWordAndShuffle()
            )
        }
        // Reset user guess
        updateUserGuess("")
    }
}

data class GameUiState(
    val currentScrambledWord: String = ""
    , val currentWordCount: Int = 0
    , val numberOfStage: Int = (5..10).random()
    , val isUserGuessedWordWrong: Boolean = false
    , val score: Int = 0
    , val isGameOver: Boolean = false
)