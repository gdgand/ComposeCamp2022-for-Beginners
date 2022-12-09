package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.R
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
    private val subTotal = "$100"

    @Before
    fun setupContent() {
        composeTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(subtotal = subTotal, options = flavours)
            }
        }
    }

    @Test
    fun selectOptionScreen_verifyContent() {
        flavours.forEach { flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_selectOption_enableNextButton() {
        composeTestRule.onNodeWithText(flavours[0])
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .assertIsEnabled()
    }
}