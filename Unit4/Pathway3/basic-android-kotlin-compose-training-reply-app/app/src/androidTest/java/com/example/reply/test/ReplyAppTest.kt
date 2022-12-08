package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  @TestAnnotations.TestCompactWidth
  fun compactDevice_verifyUsingBottomNavigation() {
    composeTestRule.setContent {
      ReplyApp(
        windowSize = WindowWidthSizeClass.Compact
      )
    }
    composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_bottom)
      .assertExists()
  }

  @Test
  @TestAnnotations.TestMediumWidth
  fun mediumDevice_verifyUsingNavigationRail() {
    composeTestRule.setContent {
      ReplyApp(
        windowSize = WindowWidthSizeClass.Medium
      )
    }
    composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_rail)
      .assertExists()
  }

  @Test
  @TestAnnotations.TestExpandedWidth
  fun expandedDevice_verifyUsingNavigationDrawer() {
    composeTestRule.setContent {
      ReplyApp(
        windowSize = WindowWidthSizeClass.Expanded
      )
    }
    composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.navigation_drawer)
      .assertExists()
  }
}