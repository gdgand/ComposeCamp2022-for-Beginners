package com.example.cupcake.test

import androidx.navigation.NavController
import com.example.cupcake.CupcakeScreen
import org.junit.Assert

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(
        expectedRouteName,
        currentBackStackEntry?.destination?.route
    )
}