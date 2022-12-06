package com.example.tiptime
import org.junit.Assert
import org.junit.Test

class TipCalculatorTests {
    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "₩2"
        val actualTip = caculateTip(amount = amount, tipPercent = tipPercent, false)
        Assert.assertEquals(expectedTip,actualTip) //값비교하기.
    }
}
