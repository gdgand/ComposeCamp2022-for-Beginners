package com.compose.camp.hun.superherocodelab.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.camp.hun.superherocodelab.R

/**
 * FontFamily에서 Font를 가져온다.
 * font를 가져올 때, [font파일, font타입]을 설정한다. (cabin_regular, FontWeight.Normal)
 * 이렇게 생성된 FontFamily를 아래 Typography에 각 TextStyle(h1, h2, body1...)에 맞는 font를 설정한다.
 *
 * 이렇게 설정된 Typography는 MaterialTheme에 설정되고, 이를 TextView의 TextStyle을 설정하면 알맞은 폰트로 설정된다.
 */
val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Cabin,
    h1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)