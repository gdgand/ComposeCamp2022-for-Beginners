package com.example.seodang.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.seodang.R

val Yeonsung = FontFamily(
    Font(R.font.yeonsung_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Yeonsung,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Yeonsung,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = Yeonsung,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Yeonsung,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)