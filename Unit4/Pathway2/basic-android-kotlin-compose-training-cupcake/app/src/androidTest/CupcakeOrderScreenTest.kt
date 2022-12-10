class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(subtotal = subTotal, options = flavours)
            }
        }

        // Then all the options are displayed on the screen.
        flavours.forEach { flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()
        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }
}