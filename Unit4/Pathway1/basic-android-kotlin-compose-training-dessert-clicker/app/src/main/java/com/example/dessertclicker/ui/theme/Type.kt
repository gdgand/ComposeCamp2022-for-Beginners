package com.example.dessertclicker.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        color = Color(0xFF008577),
        fontSize = 33.sp,
    ),
    h6 = TextStyle(
        color =  Color(0x99000000),
        fontSize = 20.sp
    )
)