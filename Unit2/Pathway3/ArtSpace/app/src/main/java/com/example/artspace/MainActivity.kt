package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var current_img by remember { mutableStateOf(3) }

//    if (current artwork is the first artwork) {
//        // Update states to show the second artwork.
//    }
//    else if (current artwork is the second artwork) {
//        // Update states to show the third artwork.
//    }
//    else if (current artwork is the last artwork) {
//        // Update state to show the first artwork.
//    }

//    ArtInfo(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center)
//    )
}

@Composable
fun ArtInfo(
    drawableResourceId: Int,
    titleResourceId: Int,
    infoResourceId : Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    )
    {
        //이미지
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        //제목, 정보
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = titleResourceId),
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            //작품정보
            Text(
                text = stringResource(id = infoResourceId),
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            //버튼
            Column(
                modifier = Modifier.padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { var a = 0 },
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f, true)
                    ) {
                        Text(text = stringResource(R.string.previous), fontSize = 20.sp)
                    }
                    Button(
                        onClick = { var a = 0 },
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f, true)
                    ) {
                        Text(text = stringResource(R.string.next), fontSize = 20.sp)
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
//        ArtSpaceApp()
        ArtInfo()
    }
}