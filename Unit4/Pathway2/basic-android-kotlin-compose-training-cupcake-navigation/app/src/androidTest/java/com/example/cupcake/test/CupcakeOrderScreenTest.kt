package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.ui.SelectOptionScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = flavors)
        }

        flavors.forEach { flavor  ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        val subTotalText = composeTestRule.activity.getString(com.example.cupcake.R.string.subtotal_price, subTotal)
        composeTestRule.onNodeWithText(subTotalText).assertIsDisplayed()

        val nextText = composeTestRule.activity.getString(com.example.cupcake.R.string.next)
        composeTestRule.onNodeWithText(nextText).assertIsNotEnabled()
    }
}