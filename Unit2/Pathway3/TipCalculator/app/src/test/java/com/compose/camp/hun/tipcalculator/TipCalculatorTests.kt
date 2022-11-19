package com.compose.camp.hun.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 로컬 테스트
 * UI가 아닌, 함수가 올바르게 작동하고있는지 확인하는 테스트.
 */
class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val exceptedTip = "$2.00"
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, roundUp = false)
        assertEquals(exceptedTip, actualTip)
    }
}