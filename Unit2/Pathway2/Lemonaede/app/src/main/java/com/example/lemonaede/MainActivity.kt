package com.example.lemonaede

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonaede.ui.theme.LemonaedeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonaedeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    drinking()
                }
            }
        }
    }
}

@Composable
fun drinking(modifier:Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {
        var result by remember { mutableStateOf(1) } //현재 단계
        var tap by remember { mutableStateOf(1) } //클릭해야하는 횟수
        var imageResource = when(result) { //이미지 가져오기
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        var stringResource = when(result){ //글자 가져오기
            1-> R.string.tree_description
            2-> R.string.Lemon_description
            3->R.string.glass_description
            else->R.string.empty_description
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = stringResource(id =stringResource), fontSize = 18.sp) //글자
            Spacer(modifier = Modifier.height(16.dp)) //간격
                Image(painter = painterResource(id = imageResource),
                    contentDescription =result.toString(),
                    modifier=Modifier
                        .wrapContentSize()
                        .border(
                            BorderStroke(2.dp, Color(105,205,216)), //테두리 두께, 색깔
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable {  //버튼
                            if(tap>1){
                                tap-=1;
                            }
                            else {
                                tap=1 //탭 바꾸기(1회)
                                if(result==1){tap = (2..4).random()}  //화면 1->2인경우는 탭의 횟수 변경
                                if(result<4) { result += 1 } //콘텐츠바꾸기(1->4)
                                else{ result=1 }//콘텐츠 바꾸기(4->1)
                            }
                        }
                        .padding(16.dp)
                )
            }
        }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonaedeTheme {
        drinking()
    }
}