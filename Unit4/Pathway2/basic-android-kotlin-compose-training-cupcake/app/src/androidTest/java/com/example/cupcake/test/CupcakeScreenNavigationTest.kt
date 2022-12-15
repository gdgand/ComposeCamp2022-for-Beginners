package com.example.cupcake.test

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
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

@get:Rule
val composeTestRule = createAndroidComposeRule<ComponentActivity>()

private lateinit var navController: TestNavHostController

@Before
fun setupCupcakeNaveHost(){
    composeTestRule.setContent {
        navController = TestNavHostController(LocalContext.current)
        navController.navigatorProvider.addNavigator(
            ComposeNavigator()
        )
        CupcakeApp(navController = navController)
    }
}

@Test
fun cupcakeNavHost_verifyStartDestination() {
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen(){    // 시작 화면에 위로 버튼이 없는지 확인
    val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
}

@Test
fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){ // 맛 화면으로 이동 확인
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
}

private fun navigateToFlavorScreen(){   // 바로 맛 화면으로 이동
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate)
        .performClick()
}

private fun getFormattedDate(): String {    // 날짜를 생성함
    val calendar = Calendar.getInstance()
    calendar.add(java.util.Calendar.DATE, 1)
    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
    return formatter.format(calendar.time)
}

private fun navigateToPickupScreen() {  // 바로 선택 화면으로 이동
    navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
}

private fun navigateToSummaryScreen() { // 바로 요약 화면으로 이동
    navigateToPickupScreen()
    composeTestRule.onNodeWithText(getFormattedDate())
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
}

private fun performNavigateUp() {   // 이전 화면으로 이동
    val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).performClick()
}

@Test
fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {  // 수령 화면으로 이동
    navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
}

@Test
fun cupcakeNavHost_clickBackOnFlavorScreen_navigatesToStartOrderScreen() {  // 맛 화면에서 위로 버튼을 클릭하여 시작 화면으로 이동
    navigateToFlavorScreen()
    performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_clickCancelOnFlavorScreen_navigatesToStartOrderScreen() {    // 맛 화면에서 취소 버튼을 클릭하여 시작 화면으로 이동
    navigateToFlavorScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_clickNextOnPickupScreen_navigatesToSummaryScreen() { // 요약 화면으로 이동
    navigateToPickupScreen()
    composeTestRule.onNodeWithText(getFormattedDate())
        .performClick()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
}

@Test
fun cupcakeNavHost_clickBackOnPickupScreen_navigatesToFlavorScreen() {  // 수령 화면에서 위로 버튼을 클릭하여 맛 화면으로 이동
    navigateToPickupScreen()
    performNavigateUp()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
}

@Test
fun cupcakeNavHost_clickCancelOnPickupScreen_navigatesToStartOrderScreen() {    // 수령 화면에서 취소를 선택하여 시작 화면으로 이동
    navigateToPickupScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_clickCancelOnSummaryScreen_navigatesToStartOrderScreen() {   // 요약 화면에서 취소를 선택하여 시작 화면으로 이동
    navigateToSummaryScreen()
    composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}
