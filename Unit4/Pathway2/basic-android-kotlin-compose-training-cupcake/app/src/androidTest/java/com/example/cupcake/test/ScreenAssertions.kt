package com.example.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert

/*
 * Test Class 를 위한 도우미 메서드를 정의
 * 반복되는 부분을 정의하여 테스트 코드자체는 보기 편하도록 하기위하여 사용
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
