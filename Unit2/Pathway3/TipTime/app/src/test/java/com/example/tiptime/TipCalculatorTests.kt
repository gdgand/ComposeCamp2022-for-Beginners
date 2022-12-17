package com.example.tiptime

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTests {
    @Test
    fun claculate_20_percent_tip_np_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "₩2"
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}