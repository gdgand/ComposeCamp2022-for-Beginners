package com.example.calculatetip

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * 안드로이드 UI 와 관련이 없는 로직 테스트(Unit Test, Local Test)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // 컴파일러에게 테스트 코드라고 알림
    @Test
    fun calculate_20_percent_tip_no_roundup() {
        // 계산할 값
        val amount = 10.0
        // 할인율
        val tipPercent = 20.0
        // 게산 예상 값
        val expectedTip = "$2.00"
        // 실제 사용중인 메소드
        val actualTip = calculateTip(
            amount = amount,
            tipPercent = tipPercent,
            false
        )
        // 예상 값과 반환 받은 값이 동일한지 비교하는 테스트 함수
        assertEquals(expectedTip, actualTip)
        // 위와같은 어션셜, 테스트 함수는 다양하게 존재하니 참고
    }
}
