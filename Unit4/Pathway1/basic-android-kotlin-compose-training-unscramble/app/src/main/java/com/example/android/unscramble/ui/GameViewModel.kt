package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {

    var userGuess by mutableStateOf("")
        private set

    private lateinit var currentWord: String

    private var usedWords: MutableSet<String> = mutableSetOf()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState())
    val uiState: StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.shuffled().first()
        if(usedWords.contains(currentWord)){
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(word)){
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame(){
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambleWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessWord: String){
        userGuess = guessWord
    }

    fun checkUserGuess() {
        if(userGuess.equals(currentWord, ignoreCase = true)){
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update{ currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    fun updateGameState(updatedScore: Int) {

        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update{ currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            _uiState.update{ currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc(),
                    currentScrambleWord = pickRandomWordAndShuffle(),
                    score = updatedScore
                )
            }
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")

    }
}