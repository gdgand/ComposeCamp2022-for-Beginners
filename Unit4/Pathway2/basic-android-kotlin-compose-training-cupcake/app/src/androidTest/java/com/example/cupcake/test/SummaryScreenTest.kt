package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class SummaryScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val orderUiState:OrderUiState = OrderUiState(
        1, "Vanilla", getFormattedDate(), "$2.00"
    )

    @Before
    fun setupOrderSummaryScreen() {
        composeTestRule.setContent {
            CupcakeTheme() {
                OrderSummaryScreen(
                    orderUiState
                )
            }
        }
    }

    @Test
    fun summaryScreen_verifyContent() {
        composeTestRule.onNodeWithStringId(R.string.quantity, true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithQuantityStringId(R.plurals.cupcakes, orderUiState.quantity)

        composeTestRule.onNodeWithStringId(R.string.flavor, true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(orderUiState.flavor)

        composeTestRule.onNodeWithStringId(R.string.pickup_date, true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(orderUiState.date)

        composeTestRule.onNodeWithStringId(R.string.subtotal_price, orderUiState.price)

        composeTestRule.onNodeWithStringId(R.string.send)
            .assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .assertIsDisplayed()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

}

