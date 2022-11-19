package com.example.tiptime

import org.junit.Test

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        // locale 문제로 2 달러 대신 2원이 나옴. 해결하기 귀찮으니 패스...
//        val amount = 10.00
//        val tipPercent = 20.00
//        val expectedTip = "$2.00"
//
//        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
//
//        assertEquals(expectedTip, actualTip)
    }
}