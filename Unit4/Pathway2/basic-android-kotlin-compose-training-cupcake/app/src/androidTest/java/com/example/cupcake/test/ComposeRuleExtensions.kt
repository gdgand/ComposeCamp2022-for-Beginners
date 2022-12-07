package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int, ignoreCase: Boolean = false,
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id), ignoreCase = ignoreCase)

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    id: Int,
    vararg formatArgs: Any,
): SemanticsNodeInteraction =
    onNodeWithText(activity.getString(id, formatArgs))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescription(
    @StringRes id: Int,
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithQuantityStringId(
    @PluralsRes id: Int, quantity: Int,
): SemanticsNodeInteraction = onNodeWithText(activity.resources.getQuantityString(id, quantity))
