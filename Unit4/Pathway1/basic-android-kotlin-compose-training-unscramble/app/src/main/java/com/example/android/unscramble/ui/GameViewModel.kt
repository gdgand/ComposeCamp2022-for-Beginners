package com.example.android.unscramble.ui

import androidx.compose.runtime.MutableState
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

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())  // 컴포저블이 UI 상태 업데이트 수신을 대기하며 구성 변경에도 화면 상태가 지속하도록 작업
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()    // 외부에서는 읽기 전용

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()  // 게임에서 사용했던 단어들

    var userGuess by mutableStateOf("")
        private set

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        if(usedWords.contains(currentWord))
            return pickRandomWordAndShuffle()
        else{
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String{
        val tempWord = word.toCharArray()

        tempWord.shuffle()
        while(String(tempWord).equals(word))
            tempWord.shuffle()

        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if(userGuess.equals(currentWord, ignoreCase = true)){   // 사용자의 추측이 맞았을 경우
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        }
        else{   // 사용자의 추측이 틀렸을 경우
            _uiState.update {
                currentState -> currentState.copy(isGuessedWordWrong = true)
            }
        }

        // user guess 초기화
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int){
        if(usedWords.size == MAX_NO_OF_WORDS){  // 마지막 라운드단계로 돌입한다
            _uiState.update {
                    currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        }
        else{   // 아직 단어가 남았다면 계속 다음 단계로 넘어간다
            _uiState.update {
                    currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc(),
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore
                )
            }
        }
    }

    fun skipWord(){
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    init {  // init이 호출할 메소드보다 위에 있으면 오류 발생!!!
        resetGame()
    }
}