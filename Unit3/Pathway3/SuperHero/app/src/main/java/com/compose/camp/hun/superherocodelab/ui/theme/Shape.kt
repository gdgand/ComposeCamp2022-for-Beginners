package com.compose.camp.hun.superherocodelab.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shape.kt 파일
 * Shape파일은 Compose에서 구셩요소의 도형을 정의하는데 사용된다.
 * 구성요소에는 소형, 중형, 대형 3가지가 존재한다.
 *
 * Card 컴포즈함수는 기본적으로 중형 사이즈로 Shape가 설정된다.
 */
val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(16.dp),
)