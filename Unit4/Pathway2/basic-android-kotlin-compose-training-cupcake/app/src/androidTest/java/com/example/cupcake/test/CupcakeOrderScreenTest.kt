package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(subtotal = subTotal, options = flavours)
            }
        }

        // Then all the options are displayed on the screen.
        flavours.forEach { flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                com.example.cupcake.R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()
        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsNotEnabled()
    }
}