package com.bnerdranch.and.tipcalculator
import org.junit.Test
import org.junit.Assert.*
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTest {




    val addCurChar : (Double) -> String = { NumberFormat.getCurrencyInstance().format(it) }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calculate_input_100_20_percent_tip_no_roundup() {
        assertEquals(addCurChar(20.0), calculateTip(100.0, 20.0, false))
    }
    @Test
    fun calculate_input_0_20_percent_tip_no_roundup() {
        assertEquals(addCurChar(0.0), calculateTip(0.0, 20.0, false))
    }
}