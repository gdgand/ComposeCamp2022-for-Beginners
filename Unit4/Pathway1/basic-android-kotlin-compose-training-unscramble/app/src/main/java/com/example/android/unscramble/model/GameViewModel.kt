package com.example.android.unscramble.model

import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.GameUiState
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
