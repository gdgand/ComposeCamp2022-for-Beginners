package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(GameUiState())
  private var _count = 0
  private lateinit var currentWord: String
  private var usedWords: MutableSet<String> = mutableSetOf()

  val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
  val count: Int
    get() = _count
  var userGuess by mutableStateOf("")
    private set

  init {
    resetGame()
  }

  fun checkUserGuess() {
    if (userGuess.equals(currentWord, ignoreCase = true)) {

    } else {
      _uiState.update { currentState ->
        currentState.copy(isGuessedWordWrong = true)
      }
    }
    updateUserGuess("")
  }

  fun updateUserGuess(guessedWord: String){
    userGuess = guessedWord
  }

  private fun resetGame() {
    usedWords.clear()
    _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
  }

  private fun pickRandomWordAndShuffle(): String {
    currentWord = allWords.random()

    return if (usedWords.contains(currentWord)) {
      pickRandomWordAndShuffle()
    } else {
      usedWords.add(currentWord)
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
}