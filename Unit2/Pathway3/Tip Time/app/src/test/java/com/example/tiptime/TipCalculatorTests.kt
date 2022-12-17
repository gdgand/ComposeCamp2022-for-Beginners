package com.example.tiptime


import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTests {
    @Test
    fun calculate_20_percent_tip_no_roundup(){
        //calculateTip에 매개변수로 보낼 값 세가지 amount, tipPercent, roundUp
        val amount=10.00
        val tipPercent=20.00
        val expectedTip="₩2"//결과로 반환되어야 할 값

        val actualTip= calculateTip(amount=amount, tipPercent=tipPercent, roundUp = false)

        assertEquals(expectedTip,actualTip)
    }
}