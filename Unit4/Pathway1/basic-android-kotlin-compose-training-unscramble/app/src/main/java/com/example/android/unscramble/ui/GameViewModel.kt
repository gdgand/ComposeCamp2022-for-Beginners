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
    private var _count : Int = 0

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    val count : Int
        get() = _count

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun getAnswer() = currentWord

    private fun pickRandomWordAndShuffle(): String {
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

    fun  resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle()
                // score reset
//                ,
        )
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {

        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessWordWrong = true)
            }
        }
        updateUserGuess("")
    }

//    private
    fun updateUserGuess(guessWord: String) {
        userGuess = guessWord
    }
}

data class GameUiState(
    val currentScrambledWord: String = ""
    , val isGuessWordWrong: Boolean = false
)
