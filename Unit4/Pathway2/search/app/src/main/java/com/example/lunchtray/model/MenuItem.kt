
package com.example.lunchtray.model

import java.text.NumberFormat

sealed class MenuItem(
    open val name: String,
    open val description: String,
    open val price: Double
) {

    data class EntreeItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class SideDishItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)

    data class AccompanimentItem(
        override val name: String,
        override val description: String,
        override val price: Double
    ) : MenuItem(name, description, price)


    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}
