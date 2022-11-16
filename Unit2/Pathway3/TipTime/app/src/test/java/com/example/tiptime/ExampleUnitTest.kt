package com.example.tiptime

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        assertEquals(calculateTip(10.0, 10.0, true), "₩1")
    }
}