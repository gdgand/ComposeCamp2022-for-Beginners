package com.wookhyun.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent: Double = 20.0
        val expectedTip = "₩2"

        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)

    }
}