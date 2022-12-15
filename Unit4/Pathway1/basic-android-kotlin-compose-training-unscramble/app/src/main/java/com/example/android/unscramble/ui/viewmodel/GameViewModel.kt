package com.example.android.unscramble.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.allWords
import com.example.android.unscramble.ui.GameUiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiStatus = MutableStateFlow(GameUiStatus())
    val uiStatus: StateFlow<GameUiStatus> = _uiStatus.asStateFlow()

    private lateinit var currentWord: String
    private val usedWord: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        return if (usedWord.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWord.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun updateUserGuess(guessedWord: String) { userGuess = guessedWord }

    fun checkUserGuess() {
        when {
            userGuess.equals(currentWord, ignoreCase = true) -> {}
            else -> {
                _uiStatus.update { currentState ->
                    currentState.copy(isGuessWordWrong = true)
                }
            }
        }
        updateUserGuess("")
    }

    fun resetGame() {
        usedWord.clear()
        _uiStatus.value = GameUiStatus(currentScrambledWord = pickRandomWordAndShuffle())
    }
}