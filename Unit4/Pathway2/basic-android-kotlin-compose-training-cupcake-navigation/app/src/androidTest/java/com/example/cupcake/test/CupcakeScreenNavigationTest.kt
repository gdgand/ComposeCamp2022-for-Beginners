package com.example.cupcake.test


import android.icu.text.SimpleDateFormat
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R.*
import com.example.cupcake.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*


class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navToSelectFlavorScreen() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
        val oneCupCakeText = composeTestRule.activity.getString(com.example.cupcake.R.string.one_cupcake)
        composeTestRule.onNodeWithText(text = oneCupCakeText, ignoreCase = true)
                .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }



    private fun navToFlavorScreen() {
        val oneCupCakeText = composeTestRule.activity.getString(com.example.cupcake.R.string.one_cupcake)
        composeTestRule.onNodeWithText(text = oneCupCakeText)
            .performClick()
        val chocolateText = composeTestRule.activity.getString(com.example.cupcake.R.string.chocolate)
        composeTestRule.onNodeWithText(text = chocolateText)
            .performClick()
    }

    private fun navFromStartToPickUpScreen() {
        navToFlavorScreen()
        val chocolateText = composeTestRule.activity.getString(com.example.cupcake.R.string.chocolate)
        composeTestRule.onNodeWithText(text = chocolateText)
            .performClick()
        clickNextButton()
    }

    private fun navFromStartToSummaryScreen() {
        navToFlavorScreen()
        clickNextButton()
        val dateText = getFormattedDate()
        composeTestRule.onNodeWithText(text = dateText).performClick()
        clickNextButton()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun clickCancelButton() {
        val cancelText = composeTestRule.activity.getString(com.example.cupcake.R.string.cancel)
        composeTestRule.onNodeWithText(text = cancelText)
            .performClick()
    }

    private fun clickNextButton() {
        val nextText = composeTestRule.activity.getString(com.example.cupcake.R.string.next)
        composeTestRule.onNodeWithText(text = nextText)
            .performClick()
    }

    @Test
    fun cupcakeNavHost_clickBackButton_fromSelectFlavorToStartScreen() {
        navToFlavorScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)

        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_fromSelectFlavorToStartScreen() {
        navToFlavorScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)

        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickBackButton_fromPickUpToFlavorScreen() {
        navFromStartToPickUpScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_fromPickUpToStartScreen() {
        navFromStartToPickUpScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButton_fromSummaryToStartScreen() {
        navFromStartToSummaryScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

}