package com.example.thirtydays.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thirtydays.R

val sriracha = FontFamily(
    Font(R.font.sriracha_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = sriracha,
    h1 = TextStyle(
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontSize = 16.sp
    )
)