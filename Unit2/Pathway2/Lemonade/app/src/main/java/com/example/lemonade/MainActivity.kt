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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(){//전체를 감싸며 로직을 처리한다
    var stageNum by remember{ mutableStateOf(0) }
    var tabNum by remember { mutableStateOf(-1) }
    var test=0

    when(stageNum){//stageNum에 따라 표시되는 ui가 달라진다
        0->{//레몬나무에서 레몬 선택하는 화면
            TextAndImage(
                messageId=stringResource(id = R.string.tap_lemon),
                imageId= painterResource(id = R.drawable.lemon_tree),
                descriptionId= stringResource(id = R.string.lemon_tree)
            ) {//전달할 onClick 처리 함수
                stageNum++
                tabNum=(2..4).random()
            }
        }
        1-> {//레몬즙을 짜는 화면
            TextAndImage(
                messageId=stringResource(id = R.string.keep_tapping),
                imageId= painterResource(id = R.drawable.lemon_squeeze),
                descriptionId= stringResource(id = R.string.lemon)
            ) {
                tabNum--
                if(tabNum==0)
                    stageNum++
            }
        }
        2-> {//레몬에이드를 마시는 화면
            TextAndImage(
                messageId=stringResource(id = R.string.drink),
                imageId= painterResource(id = R.drawable.lemon_drink),
                descriptionId= stringResource(id = R.string.glass_of_lemonade)
            ) {
                stageNum++
            }
        }
        3-> {//빈컵 화면
            TextAndImage(
                messageId=stringResource(id = R.string.start_again),
                imageId= painterResource(id = R.drawable.lemon_restart),
                descriptionId= stringResource(id = R.string.empty_glass)
            ) {
                stageNum=0
            }
        }
    }
}

@Composable
fun TextAndImage(//ui표기를 담당한다
    messageId:String,
    imageId: Painter,
    descriptionId:String,
    onClickFun: ()->Unit //()->Unit 타입의 함수를 매개변수로 받는다
){
    Column(
        modifier = Modifier.fillMaxWidth(),//가로를 차지가능한 만큼 다 차지한다
        horizontalAlignment = Alignment.CenterHorizontally,//가로중앙
        verticalArrangement = Arrangement.Center//세로중앙
    ) {
        Text(
            text = messageId,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            onClick = onClickFun,
            modifier = Modifier.border(
                BorderStroke(2.dp, Color(105,205,216)),
                shape= RoundedCornerShape(4.dp)
            )
        ) {
            Image(
                painter = imageId,
                contentDescription = descriptionId,
            )
        }
    }
}