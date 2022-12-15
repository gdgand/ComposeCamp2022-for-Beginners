package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.R
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
import onNodeWithStringId
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_nextButton() {

        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            CupcakeTheme() {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }

        composeTestRule.onNodeWithText("Vanilla").performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()

    }
    @Test
    fun selectOptionScreen_verifyContent() {

        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            CupcakeTheme() {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }

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
    fun startOrderScreen_verifyContent() {
        val quantityOptions = listOf(
            Pair(R.string.one_cupcake, 1),
            Pair(R.string.six_cupcakes, 6),
            Pair(R.string.twelve_cupcakes, 12)
        )
        composeTestRule.setContent {
            CupcakeTheme() {
                StartOrderScreen(
                    quantityOptions = quantityOptions,
                    onNextButtonClicked = {}
                )
            }
        }

        quantityOptions.forEach { quantityOption ->
            composeTestRule.onNodeWithStringId(quantityOption.first).assertIsDisplayed()
        }
    }

    @Test
    fun summaryScreen_verifyDisplay() {
        val uiState = OrderUiState(
            quantity = 1,
            "Coffee",
            "Sun Dec 18",
            price = "$24.00",
            pickupOptions = listOf()
        )
        composeTestRule.setContent {
            CupcakeTheme() {
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {},
                    onSendButtonClicked ={ _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithText(uiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(uiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                uiState.price
            )
        ).assertIsDisplayed()
    }

}