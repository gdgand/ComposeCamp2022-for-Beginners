package com.example.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

class ScreenAssertions {
}

fun NavController.assertCurrentRouteName(expectedRouteName: String){
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}