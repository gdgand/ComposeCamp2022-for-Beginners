package com.example.cupcake.test

import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.NavHostController
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.compose.ui.test.onNodeWithContentDescription

import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            CupcakeApp(navController = navController)
        }

    }

    fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
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

}