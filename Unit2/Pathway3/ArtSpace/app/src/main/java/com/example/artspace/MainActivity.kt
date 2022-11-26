package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var state by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageAndInfo(state)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { state-- } ) {
                Text(stringResource(R.string.previous))
            }
            Button(onClick = { state++ } ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun ImageAndInfo(
    state: Int
){

    @DrawableRes var imageId: Int = R.drawable.img_1
    @StringRes var imageDescription: Int = R.string.img_desc_1
    @StringRes var title: Int = R.string.title_1
    @StringRes var detail: Int = R.string.detail_1

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when(state) {
            1 -> {
                imageId = R.drawable.img_1
                imageDescription = R.string.img_desc_1
                title = R.string.title_1
                detail = R.string.detail_1
            }
            2 -> {
                imageId = R.drawable.img_2
                imageDescription = R.string.img_desc_2
                title = R.string.title_2
                detail = R.string.detail_2
            }
            3 -> {
                imageId = R.drawable.img_3
                imageDescription = R.string.img_desc_3
                title = R.string.title_3
                detail = R.string.detail_3
            }
            4 -> {
                imageId = R.drawable.img_4
                imageDescription = R.string.img_desc_4
                title = R.string.title_4
                detail = R.string.detail_4
            }
            5 -> {
                imageId = R.drawable.img_5
                imageDescription = R.string.img_desc_5
                title = R.string.title_5
                detail = R.string.detail_5
            }
            6 -> {
                imageId = R.drawable.img_6
                imageDescription = R.string.img_desc_6
                title = R.string.title_6
                detail = R.string.detail_6
            }
        }

        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black)
        ) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp

            if (screenWidth > 800.dp) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = imageDescription),
                    modifier = Modifier.padding(30.dp)
                        .fillMaxHeight(0.7F),
                    contentScale = ContentScale.Crop
                )
            }
            else{
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = imageDescription),
                    modifier = Modifier.padding(30.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.7F),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Companion.Gray,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = stringResource(id = title),
                fontSize = 36.sp,
                modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 5.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = stringResource(id = detail),
                modifier = Modifier.padding(25.dp, 5.dp, 25.dp, 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}