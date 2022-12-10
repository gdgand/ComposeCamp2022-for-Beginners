package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test
import com.example.reply.R

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        // Given third email is displayed
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        // Open detailed page
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        // Verify that it shows the detailed screen for the correct email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that it still shows the detailed screen for the same email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Setup expanded window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        // Given third email is displayed
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        // Select third email
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()


        //details_screen 찾을 수 없다고 에러나는듯.
        // Verify that third email is displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body)))

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that third email is still displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body)))
    }
}