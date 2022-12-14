package com.example.cupcake.test

import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.NavHostController

import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    //private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            /*
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            //CupcakeApp()
            CupcakeApp(navController = navController)
            */
        }


    }
}