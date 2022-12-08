package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.ReplyApp
import com.example.reply.*
import org.junit.Rule
import org.junit.Test
import com.example.reply.R.string.navigation_bottom
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.junit.Before

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupReplyAppTest() {

    }

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }
//        composeTestRule.onNodeWithTagForStringId(id = R.string.navigation_bottom)
        composeTestRule.onNodeWithTagForStringId(id = com.example.reply.R.string.navigation_bottom)
            .assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        // Set up medium window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }
        // Navigation rail is displayed
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_rail)
            .assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Set up expanded window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }
        // Navigation drawer is displayed
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_drawer)
            .assertExists()
    }
}