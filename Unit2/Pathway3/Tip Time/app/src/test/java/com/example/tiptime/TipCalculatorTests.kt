package com.example.tiptime

import junit.framework.Assert.assertEquals
import org.junit.Test

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        // expectedTip이 $로 돼 있지만, 한국에서 실행할 경우 원으로 표기되기 때문에 테스트에 실패함
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "$2.00"
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, roundUp = false)

        assertEquals(expectedTip, actualTip)
    }
}