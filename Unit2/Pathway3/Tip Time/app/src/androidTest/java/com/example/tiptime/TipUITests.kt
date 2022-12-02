package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test

class TipUITests {
    @get:Rule
    val composeTestRule= createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {//UI테스트를 위해 UI부분을 불러옴
                TipTimeScreen()
            }
        }
        composeTestRule.onNodeWithText("Cost of Service") //이 문자열 적혀있는 TextField에 엑세스함
            .performTextInput("10")//접근한 TextFiled에 10이라는 값을 입력하도록 만듦
        composeTestRule.onNodeWithText("Tip (%)").performTextInput("20")

        composeTestRule.onNodeWithText("Tip Amount: ₩2").assertExists()//이런 내용이 적혀있는게 있나 확인
    }

}