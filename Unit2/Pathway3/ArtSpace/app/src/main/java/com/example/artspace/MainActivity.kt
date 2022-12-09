package com.example.artspace
//화면상태에따른 변화만들어보기(도전과제)
import android.os.Bundle
import android.text.style.BackgroundColorSpan
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    artspacepage()
                }
            }
        }
    }
}
@Composable
fun artspacepage(
    modifier: Modifier= Modifier
        .fillMaxSize() //화면꽉채우기
        .wrapContentSize(Alignment.Center) //콘텐츠들을 중앙정렬
){
    var result by remember { mutableStateOf(2)}

    var imageResource=when(result){
        1-> R.drawable.maru
        2-> R.drawable.maru2
        else->R.drawable.maru3
    }

    var stringResource= when(result){
        1->R.string.rightmaru
        2->R.string.drinkmaru
        else->R.string.listenmaru
    }
    var stringsubResource= when(result){
        1->R.string.rightmaru_desc
        2->R.string.drinkmaru_desc
        else->R.string.listenmaru_desc
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFe4caaa)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

            ){
            Image(
                painter = painterResource(id =imageResource),
                contentDescription =result.toString(),
                modifier = Modifier
                    .height(400.dp)
                    .width(350.dp)
            )

        Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center, //세로중앙정렬
            ){
                Text(
                    text = stringResource(id = stringResource),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = stringsubResource),
                    textAlign = TextAlign.Center
                )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row() {
            Button(
                modifier = Modifier.width(140.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFba813b), contentColor = Color.White),
                onClick = {
                    if(result<4)
                        result++
                    else
                        result=1
                }) { Text(text = stringResource(R.string.previous))}
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFba813b), contentColor = Color.White),
                modifier = Modifier.width(140.dp),
                onClick = {
                    if(result>1)
                        result--
                    else
                        result=3
                }) {Text(text = stringResource(R.string.next))}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        artspacepage()
    }
}