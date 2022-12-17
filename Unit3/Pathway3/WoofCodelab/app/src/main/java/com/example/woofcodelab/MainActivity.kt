/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.woofcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woofcodelab.data.Dog
import com.example.woofcodelab.data.dogs
import com.example.woofcodelab.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                WoofApp()
            }
        }
    }
}


/**
 * Composable that displays an app bar and a list of dogs.
 */
@Composable
fun WoofApp() {
    Scaffold(
        topBar = { WoofTopAppBar()}
    ) {paddingValues->//https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used 참고
        LazyColumn (//modifier 배경색을 아래처럼하면 Theme.kt에서 background로 설정된 색이 설정됨
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(paddingValues)
        ){
            items(dogs) {//lazycolumn에 dogs만큼의 데이터가 DogItem의 모양대로 표기됨
                DogItem(dog = it)
            }
        }
    }

}

@Composable
fun WoofTopAppBar(modifier: Modifier=Modifier){//상단 바
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically//Row의 모든것이 세로 가운데
    ){
        Image(
            modifier= Modifier
                .size(64.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    var expanded by remember{ mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
    )
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp//그림자
    ) {//row에 모양을 적용할수없음, 따라서 Card씀, 얘는 안에 단일 컴포저블 포함가능하고 장식을 하는데 씀(테두리, 도형 이런거)
        //Card는 중형구성요소(?)이므로 Shapes.kt에서 Shapes객체의 medium 속성으로 모양이 정해짐
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(//애니메이션 상세 설정?
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )//크기 변할때 애니매이션 들어감
                .background(color=color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                // .background(MaterialTheme.colors.surface)//Theme.kt에서 surface로 한것 적용됨
                //상위 컴포저블 Card는 Surface가 자동 적용되므로 위 줄을 안해도 알아서 Surfcae색 적용됨
            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(Modifier.weight(1f))
                //Row안에서 spacer가 가중치 있는 유일한 요소이므로 다른요소들이 공간채우고 남는공간을 spacer가 채음
                DogItemButton(
                    expanded = expanded,
                    onClick = { expanded=!expanded }
                )

            }
            if(expanded){//expanded가 true일때만 개의 취미부분이 보임
                DogHobby(dogHobby = dog.hobbies)
            }

        }


    }

}
@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector =
                if(expanded)
                    Icons.Filled.ExpandMore//메테리얼 디자인에 있는 디자인
                else
                    Icons.Filled.ExpandLess
            ,
            tint=MaterialTheme.colors.secondary,
            contentDescription = stringResource(id = R.string.expand_button_content_description)
        )
    }
}
/**
 * Composable that displays a photo of a dog.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(//와 이렇게 원형이미지 적용이 쉽다고?
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),//이미지 원형으로
        contentScale=ContentScale.Crop,//가로, 세로가 모자란 경우 완전한 원모양이 안됨 이것을 적용해야 도형 모양에 맞춰서 이미지를 잘라줌
        painter = painterResource(dogIcon),
        /*
         * Content Description is not needed here - image is decorative, and setting a null content
         * description allows accessibility services to skip this element during navigation.
         */
        contentDescription = null
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
           // color=MaterialTheme.colors.onSurface,//글자색이 Theme.kt의 onSurface색상됨
           //DogInformation은 Card위 즉 Surface위이므로 onSurface색이 자동 적용됨 그래서 위 코드 안적어도 됨
            style=MaterialTheme.typography.h2,//Type.kt에 있는 h2적용
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.body1
        )
    }
}
@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.body1,
        )
    }
}
/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {//ui.theme의 Theme.kt에서 46번줄에 false값 전달
        WoofApp()
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}