package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test

class TipUITests {
    @get:Rule // 주석
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        // UI content 설정
        composeTestRule.setContent {
            TipTimeTheme() {
                TipTimeScreen()
            }
        }

        composeTestRule.onNodeWithText("Bill Amount") // TextField 컴포저블에 액세스
            .performTextInput("10") // 서비스 비용

        composeTestRule.onNodeWithText("Tip (%)") // OutlinedTextField
            .performTextInput("20") // 팁 비율

        composeTestRule.onNodeWithText("Tip amount: $2.00").assertExists() // 이 텍스트가 있는 노드가 존재한다

    }

}