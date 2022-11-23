package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val uiState: StateFlow<GameUiState> get() = savedStateHandle.getStateFlow(GAME_UI_STATE_KEY, GameUiState())

    private lateinit var currentWord: String

    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

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

        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            savedStateHandle.get<GameUiState>(GAME_UI_STATE_KEY)?.also { gameUiState ->
                savedStateHandle[GAME_UI_STATE_KEY] = gameUiState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            savedStateHandle.get<GameUiState>(GAME_UI_STATE_KEY)?.also { currentState ->
                savedStateHandle[GAME_UI_STATE_KEY] = currentState.copy(
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    isGuessedWordWrong = false,
                    score = updatedScore
                )
            }
        }
    }

    fun resetGame() {
        usedWords.clear()
        savedStateHandle[GAME_UI_STATE_KEY] = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = savedStateHandle.get<GameUiState>(GAME_UI_STATE_KEY)?.score?.plus(SCORE_INCREASE) ?: 0

            updateGameState(updatedScore)
        } else {
            savedStateHandle.get<GameUiState>(GAME_UI_STATE_KEY)?.also { currentState ->
                savedStateHandle[GAME_UI_STATE_KEY] = currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    fun skipWord() {
        updateGameState(savedStateHandle.get<GameUiState>(GAME_UI_STATE_KEY)?.score ?: 0)
        // Reset user guess
        updateUserGuess("")
    }

    init {
        resetGame()
    }

    companion object {
        private const val GAME_UI_STATE_KEY = "game_ui_state"
    }
}