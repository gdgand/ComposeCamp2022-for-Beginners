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

        assertEquals(viewModel.getAnswer(), correctPlayerWord)

        viewModel.checkUserGuess()

//        assertEquals(viewModel.getAnswer(), correctPlayerWord)

        currentGameUiState = viewModel.uiState.value

        assertEquals("실패 시", viewModel.getAnswer(), correctPlayerWord)

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

        repeat(currentGameUiState.numberOfStage) {
            expectedScore += SCORE_INCREASE
            loopCount += 1
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()

            assertFalse(currentGameUiState.isUserGuessedWordWrong)
            assertEquals(viewModel.getAnswer(), correctPlayerWord)
        }

        // Assert that after each correct answer, score is updated correctly.

        assertEquals(loopCount, currentGameUiState.numberOfStage)
        assertEquals(expectedScore, currentGameUiState.score)
    }

}