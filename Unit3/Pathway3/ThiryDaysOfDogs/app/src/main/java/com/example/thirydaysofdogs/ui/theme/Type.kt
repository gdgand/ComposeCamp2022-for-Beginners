package com.example.thirydaysofdogs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thirydaysofdogs.R

val srirachaRegular = FontFamily(
    Font(R.font.sriracha_regular)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = srirachaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = srirachaRegular,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = srirachaRegular,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = srirachaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)