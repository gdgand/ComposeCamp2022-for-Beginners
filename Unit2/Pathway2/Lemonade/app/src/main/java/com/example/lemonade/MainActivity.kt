package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}
/*
[요구사항]
- 텍스트는 늘 18sp
- 레이블과 이미지는 16dp 마진
- 이미지 주위에 둥근 얇은 테두리  -> Border
(105 for Red, 205 for Green, and 216 for Blue.)
두께 2dp, 라운드 코너 4dp

*/
/*
현재 단계를 나타낼 step 변수가 필요하겠다.
컴포즈블 함수는 하나면 되려나?
*/

@Preview(showBackground = true)
@Composable
fun LemonApp() {

    /*
    1 -> lemon tree
    2 -> lemon
    3 -> Glass of lemonade
    4 -> Empty glass
    */

    var currentStep by remember {
        mutableStateOf(1)
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    R.string.lemontree_info,
                    R.drawable.lemon_tree,
                    R.string.lemontree_content_description
                ) {
                    currentStep = 2
                }
            }
            2 -> {
                val needSqueezeCount = (2..4).random()
                var didSqueezeCount = 0

                LemonTextAndImage(
                    R.string.lemon_info,
                    R.drawable.lemon_squeeze,
                    R.string.lemon_content_description
                ) {
                    didSqueezeCount++
                    if(needSqueezeCount == didSqueezeCount){
                        currentStep = 3
                    }
                }
            }
            3 -> {
                LemonTextAndImage(
                    R.string.glassoflemonade_info,
                    R.drawable.lemon_drink,
                    R.string.glassoflemonade_content_description
                ) {
                    currentStep = 4
                }
            }
            4 -> {
                LemonTextAndImage(
                    R.string.emptyglass_info,
                    R.drawable.lemon_restart,
                    R.string.emptyglass_content_description
                ) {
                    currentStep = 1
                }
            }
        }

    }
}

// @Preview(showBackground = true)
@Composable
fun LemonTextAndImage(
    textResource: Int,
    imageResource: Int,
    imageContentDescriptionResource: Int,
    onClicked: () -> Unit,
) {
    Column(
        // modifier = Modifier.wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            // border = BorderStroke(2.dp, Color(0xFF69CDD8)),
            // shape = RoundedCornerShape(30.dp),
            // elevation = null,
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
            modifier = Modifier.border(2.dp, Color(0xFF69CDD8), shape = RoundedCornerShape(30.dp))
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = imageContentDescriptionResource),
            )
        }
    }
}

/*
솔루션을 보니까,
squeezeCount도 리멤버로 정하고,
(근데, 내가 짜둔거도 디버깅해보면 잘되는데..?)
솔루션 대로 하면 클릭할때마다 리컴포지션되서 별로인거 아닌가???
이미지도 modifier에 클릭할 수 있게 하는게 있네
*/