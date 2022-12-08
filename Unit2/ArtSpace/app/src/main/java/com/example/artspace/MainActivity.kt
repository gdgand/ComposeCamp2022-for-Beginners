package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        modifier = modifier.fillMaxSize(),
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

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally //글자들을 중앙정렬해주어요
        ) {
            Text(
                text = stringResource(id =stringResource),
                fontSize =28.sp,
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
                onClick = {
                    if(result<4)
                        result++
                    else
                        result=1
                }) { Text(text = "Previous")}
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                modifier = Modifier.width(140.dp),
                onClick = {
                    if(result>1)
                        result--
                    else
                        result=3
                }) {Text(text = "Next")}
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