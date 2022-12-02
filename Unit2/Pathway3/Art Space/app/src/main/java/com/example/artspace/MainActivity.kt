package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                   ArtSpaceApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtSpaceApp(){
    var imageNum by remember {
        mutableStateOf(1)
    }
    when(imageNum){
        1->{
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                ImageAndDescribe(
                    imageId = painterResource(id = R.drawable.the_daughter_of_tech),
                    imageDescribeId = stringResource(id = R.string.the_daughter_of_tech),
                    imageTitleId = stringResource(id = R.string.the_daughter_of_tech),
                    artestAndYear = stringResource(id = R.string.the_daughter_of_tech_describe)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row() {
                    MoveButton(
                        text = "Previous",
                        imageNum=imageNum
                    ) {
                        imageNum=3
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    MoveButton(
                        text = "Next",
                        imageNum=imageNum
                    ) {
                        imageNum++
                    }
                }
            }
        }
        2->{
            Column(
                modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageAndDescribe(
                    imageId = painterResource(id = R.drawable.rabbit),
                    imageDescribeId = stringResource(id = R.string.rabbit),
                    imageTitleId = stringResource(id = R.string.rabbit),
                    artestAndYear = stringResource(id = R.string.rabbit_describe)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row() {
                    MoveButton(
                        text = "Previous",
                        imageNum=imageNum
                    ) {
                        imageNum--
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    MoveButton(
                        text = "Next",
                        imageNum=imageNum
                    ) {
                        imageNum++
                    }
                }
            }
        }
        3->{
            Column(
                modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageAndDescribe(
                    imageId = painterResource(id = R.drawable.the_treachery_of_apple),
                    imageDescribeId = stringResource(id = R.string.the_treachery_of_apple),
                    imageTitleId = stringResource(id = R.string.the_treachery_of_apple),
                    artestAndYear = stringResource(id = R.string.the_treachery_of_apple_describe)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row() {
                    MoveButton(
                        text = "Previous",
                        imageNum=imageNum
                    ) {
                        imageNum--
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    MoveButton(
                        text = "Next",
                        imageNum=imageNum
                    ) {
                        imageNum=1
                    }
                }
            }
        }
    }


}
@Composable
fun ImageAndDescribe(
    imageId:Painter,
    imageDescribeId:String,
    imageTitleId:String,
    artestAndYear:String,
){
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        androidx.compose.material.Surface(
            modifier = Modifier
                .background(Color(255, 255, 255, 255))
                .border(width = 2.dp, color = Color.DarkGray, shape = RectangleShape)
                .shadow(10.dp)//이거 shadow보다 border를 먼저 적으면 이미지 근처로 그림자 생김;; 뭐지
                .wrapContentSize()
                .padding(20.dp)
        ) {
            Image(
                painter = imageId,
                contentDescription = imageDescribeId,
                modifier = Modifier.size(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        androidx.compose.material.Surface(
            modifier = Modifier
                .shadow(5.dp)
                .wrapContentWidth()
                .padding(19.dp)
        ) {
            ArtTexts(imageTitleId = imageTitleId, artestAndYear = artestAndYear)
        }
    }
}

@Composable
fun ArtTexts(
    imageTitleId:String,
    artestAndYear:String
){
    Column(
        modifier = Modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtText(
            text = imageTitleId,
            fontSize = 30,
            fontWeight = FontWeight(800)
        )
        ArtText(
            text = artestAndYear,
            fontSize = 20,
            fontWeight = FontWeight(300)
        )
    }
}
@Composable
fun ArtText(
    text:String,
    fontSize:Int,
    fontWeight: FontWeight
){
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = fontWeight
    )
}

@Composable
fun MoveButton(
    text:String,
    imageNum:Int,
    onClick:()->Unit
){
    Button(
        onClick = onClick,
        Modifier.width(120.dp)
    ) {
        Text(text = text)
    }
}