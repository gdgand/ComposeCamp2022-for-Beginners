package com.example.lunchtray.model

import android.annotation.SuppressLint
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
enum class LunchTrayScreen(@StringRes val title: String) {
    StartOrder(title = "Lunch Tray"),
    EntreeMenu(title = "Choose Entree"),
    SideDishMenu(title = "Choose Side dish"),
    AccompanimentMenu(title = "Choose Accompaniment"),
    Checkout(title = "Order Checkout")
}