package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.StartOrderScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Rule
import org.junit.Test

class StartScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            CupcakeTheme() {
                StartOrderScreen(quantityOptions = DataSource.quantityOptions, onNextButtonClick = {

                })
            }
        }

        //이미지 확인하는 방법?

        composeTestRule.onNodeWithStringId(R.string.order_cupcakes)
            .assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.six_cupcakes)
            .assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.twelve_cupcakes)
            .assertIsDisplayed()
    }
}