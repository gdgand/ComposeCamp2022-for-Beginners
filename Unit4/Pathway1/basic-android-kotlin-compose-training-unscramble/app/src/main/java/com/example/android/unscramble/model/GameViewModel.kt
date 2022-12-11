package com.example.android.unscramble.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.GameUiState
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())

    // 가변 _uiState 를 불변으로 변경
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var _count = 0

    val count: Int
        get() = _count

    // 랜덤한 단어를 저장
    private lateinit var currentWord: String

    // 게임에서 사용된 단어 저장
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    // 게임을 초기화
    fun resetGame() {
        // 사용된 단어를 초기화
        usedWords.clear()
        // 랜덤한 단어 다시 세팅
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    // 유저가 작성한 정답을 넘겨받음
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    // 유저가 작성한 정답 체크
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // 정답을 맞췄을경우 점수 증가
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // 유저의 정답이 틀렸을 경우 동작
            _uiState.update { currentState ->
                // copy 메소드를 사용하여 하나의 값만 변경 가능
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    // 스킵을 눌렀을경우 세팅
    fun skipWord() {
        // 점수는 그대로두고 단어 재세팅
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    // Game State 업데이트
    private fun updateGameState(updatedScore: Int) {
        // 푼 문제수가 최대 단어수를 넘겼는지 확인
        if (usedWords.size == MAX_NO_OF_WORDS) {
            // 넘었다면 게임 종료
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            // 넘지 않았다면 게임 계속해서 진행
            _uiState.update { currentState ->
                currentState.copy(
                    // 정답을 맞췄을경우 세팅
                    isGuessedWordWrong = false,
                    // 푼 문제 개수 1증가
                    currentWordCount = currentState.currentWordCount.inc(),
                    // 문제 단어 재 세팅
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    // 점수 업데이트
                    score = updatedScore
                )
            }
        }
    }

    private fun pickRandomWordAndShuffle(): String {
        // 랜덤한 단어를 가져옴
        currentWord = allWords.random()
        // 랜덤 선택된 단어가 이미 사용된 단어라면 다시 반복
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            // 사용되지 않았다면 단어 저장 후 메소드 호출
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    // 단어를 섞어주기 위한 메소드
    private fun shuffleCurrentWord(word: String): String {
        // shuffle 메소드를 지원하는 charArray 객체로 캐스팅해줌
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        // 단어를 섞었음에도 동일한 단어 배치라면 다시 섞음
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        // 아닐경우 String 객체로 캐스팅후 리턴
        return String(tempWord)
    }
}
