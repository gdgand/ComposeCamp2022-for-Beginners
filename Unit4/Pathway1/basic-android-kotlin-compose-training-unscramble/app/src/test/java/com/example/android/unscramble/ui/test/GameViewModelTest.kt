package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.getUnscrambledWord
import com.example.android.unscramble.ui.GameViewModel
import org.junit.Test
import android.util.Log
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import junit.framework.Assert.*
import org.junit.Assert.assertNotEquals

class GameViewModelTest {

    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value

        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        // Assert that current word is scrambled.
        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        // Assert that current word count is set to 1.
        assertTrue(gameUiState.currentWordCount == 1)
        // Assert that initially the score is 0.
        assertTrue(gameUiState.score == 0)
        // Assert that the wrong word guessed is false.
        assertFalse(gameUiState.isUserGuessedWordWrong)
        // Assert that game is not over.
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {

        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)


        viewModel.updateUserGuess(correctPlayerWord)

        var viewModelAnswer = viewModel.getAnswer()
//        assertEquals(viewModelAnswer, correctPlayerWord)

        viewModel.checkUserGuess()

//        assertEquals(viewModelAnswer, correctPlayerWord)

        currentGameUiState = viewModel.uiState.value




        // Assert that checkUserGuess() method updates isGuessedWordWrong is updated correctly.
        assertFalse(currentGameUiState.isUserGuessedWordWrong)

        // Assert that score is updated correctly.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)

//        assertEquals(viewModel.getAnswer(), correctPlayerWord)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }


    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        // Given an incorrect word as input
        val incorrectPlayerWord = "and"
        var oldGameUiState = viewModel.uiState.value

        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        var newGameUiState = viewModel.uiState.value

        assertTrue(newGameUiState.isUserGuessedWordWrong)
        assertEquals(0, newGameUiState.score - oldGameUiState.score)

    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        var  loopCount = 0
        var viewModelAnswer = viewModel.getAnswer()

        repeat(currentGameUiState.numberOfStage) {

            loopCount += 1
            viewModel.updateUserGuess(correctPlayerWord)

            viewModelAnswer = viewModel.getAnswer()
            expectedScore += SCORE_INCREASE

            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value


            assertEquals("Comparison in loop $loopCount", viewModelAnswer, correctPlayerWord )
            assertFalse("isUserGuessWrong", currentGameUiState.isUserGuessedWordWrong)

            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            viewModelAnswer = viewModel.getAnswer()
        }

        // Assert that after each correct answer, score is updated correctly.

        assertEquals("loopCount", loopCount, currentGameUiState.numberOfStage)
        assertEquals("expectedCore", expectedScore, currentGameUiState.score)
    }



    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value
        // Assert that score remains unchanged after word is skipped.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
        // Assert that word count is increased by 1 after word is skipped.
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }
}
