package com.example.tiptime
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeScreen()
            }
        }
        //특정 텍스트가 포함된 노드에 엑세스
        composeTestRule.onNodeWithText("Cost of Service")
            .performTextInput("10") //서비스 비용에 관한 필드=10
        composeTestRule.onNodeWithText("Tip (%)")
            .performTextInput("20")
        composeTestRule.onNodeWithText("Tip Amount: $2.00")
            .assertExists()
    }
}