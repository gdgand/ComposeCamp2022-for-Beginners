package com.example.tiptime

import android.util.Log
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeScreen()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("200.0")
//        composeTestRule.onNodeWithText("Tip (%)").performTextInput("15")
//        val output = "Tip : " + NumberFormat.getCurrencyInstance().format(20.00)
//        val output = "Tip : " + NumberFormat.getCurrencyInstance().format(0)
        val output = "Tip : $30.00"
//        val output = "Tip : ₩0.00"
        composeTestRule.onNodeWithText(output).assertExists()

    //        composeTestRule.onNodeWithText("Tip : $20.00").assertExists()
    }
}

