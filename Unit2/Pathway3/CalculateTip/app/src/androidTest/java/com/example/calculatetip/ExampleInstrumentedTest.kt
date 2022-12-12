package com.example.calculatetip

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.calculatetip.ui.theme.CalculateTipTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing)
 *
 * 안드로이드 UI 관련 테스트 진행
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.calculatetip", appContext.packageName)
    }

    // Activity 실행을 위한 룰
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        // 액티비티 내부의 해당 메소드 실행
        composeTestRule.setContent {
            CalculateTipTheme {
                TipTimeScreen()
            }
        }
        // 특정 텍스트가 포함된 노드로 접근(Cost of Service)
        composeTestRule.onNodeWithText("Cost of Service")
            // 해당 값 입력
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip (%)").performTextInput("20")
        // 해당 텍스트가 있는 노드가 존재해야 한다는 어선셜 실행
        composeTestRule.onNodeWithText("Tip amount: $2.00").assertExists()
    }
}
