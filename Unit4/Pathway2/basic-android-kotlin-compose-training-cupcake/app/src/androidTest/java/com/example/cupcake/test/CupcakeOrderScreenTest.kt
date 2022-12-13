package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.R
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
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
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()
        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        val quantityOptions = listOf(
            Pair(R.string.one_cupcake, 1),
            Pair(R.string.six_cupcakes, 6),
            Pair(R.string.twelve_cupcakes, 12)
        )

        composeTestRule.setContent {
            CupcakeTheme {
                StartOrderScreen(quantityOptions = quantityOptions)
            }
        }

        // Then all the options are displayed on the screen.
        quantityOptions.forEach { options ->
            composeTestRule.onNodeWithStringId(options.first).assertIsDisplayed()
        }

        composeTestRule.onNodeWithStringId(R.string.order_cupcakes).assertIsDisplayed()
    }

    @Test
    fun summaryScreen_verifyContent() {
        val orderUiState = OrderUiState(0, "FlavorValue", "DateValue", "$300.00")

        composeTestRule.setContent {
            CupcakeTheme {
                OrderSummaryScreen(
                    orderUiState = orderUiState,
                )
            }
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getQuantityString(
                R.plurals.cupcakes,
                orderUiState.quantity,
                orderUiState.quantity
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(orderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(orderUiState.date).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                orderUiState.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun selectOptionScreen_verifyNextButton() {
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

        composeTestRule.onNodeWithText(flavours.first()).performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}