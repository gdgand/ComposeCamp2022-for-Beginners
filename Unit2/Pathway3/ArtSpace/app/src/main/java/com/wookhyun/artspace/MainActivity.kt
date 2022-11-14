package com.wookhyun.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wookhyun.artspace.dto.Art
import com.wookhyun.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var index by remember { mutableStateOf(0) }
    val arts = listOf<Art>(
        Art(title = "Jack and Finn",
            description = "Hello World",
            drawbleId = R.drawable.finn_and_jack,
            year = 2022),
        Art(title = "Jack",
            description = "Hello World",
            drawbleId = R.drawable.jack,
            year = 2021),
        Art(title = "Squidward",
            description = "Hello World",
            drawbleId = R.drawable.squarepants,
            year = 2020),
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        ArtFrame(image = painterResource(id = arts[index].drawbleId),
            title = arts[index].title,
            desc = arts[index].description,
            year = arts[index].year,
            modifier = Modifier
                .weight(8f, true)
                .fillMaxWidth(.9f))
        OperationRow(onPrevious = {
            index = if (index == 0) arts.lastIndex else (index - 1) % arts.size
        },
            onNext = { index = (index + 1) % arts.size },
            modifier = Modifier
                .weight(2f)
        )
    }
}

@Composable
fun ArtFrame(
    image: Painter,
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    year: Int,
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Surface(modifier = modifier
            .padding(vertical = 16.dp)
            .weight(8f)
            .border(width = 1.dp, color = Color.Black),
            elevation = 9.dp) {
            Image(painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(24.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))
        ArtDescription(title = title, description = desc, year = year, modifier = Modifier
            .fillMaxWidth(.9f)
            .weight(2f))
    }
}

@Composable
fun ArtDescription(title: String, description: String, modifier: Modifier, year: Int) {
    Surface(
        modifier = modifier
            .shadow(9.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = title, fontSize = 22.sp)
            Row {
                Text(text = description, fontWeight = FontWeight.Bold)
                Text(text = "($year)")
            }
        }
    }
}

@Composable
fun OperationRow(onPrevious: () -> Unit, onNext: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth(.9f)
            .padding(16.dp),
    ) {
        Button(onClick = onPrevious, modifier = Modifier.weight(3f)) {
            Text(text = stringResource(id = R.string.previous))
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onNext, modifier = Modifier.weight(3f)) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun DefaultPreview1() {
    ArtSpaceTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ArtSpaceTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PaddingExample1() {
    Box(
        Modifier
            .padding(20.dp)
            .border(2.dp, Color.Green)
            .padding(20.dp)
            .border(2.dp, Color.Red)
            .size(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PaddingExample1_2() {
    Box(
        Modifier
            .border(2.dp, Color.Green)
            .border(2.dp, Color.Red)
            .padding(20.dp)
            .padding(20.dp)
            .size(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PaddingExample2() {
    Box(
        Modifier
            .border(2.dp, Color.Green)
            .padding(20.dp)
            .border(2.dp, Color.Red)
            .padding(20.dp)
            .size(200.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PaddingExample3() {
    Box(
        Modifier
            .border(2.dp, Color.Green)
            .padding(20.dp)
            .size(200.dp)
            .padding(20.dp)
            .border(2.dp, Color.Red)
    )
}


@Preview(showBackground = true)
@Composable
fun PaddingExample4() {
    Box(
        Modifier
            .border(2.dp, Color.Green)
            .size(200.dp)
            .padding(20.dp)
            .padding(20.dp)
            .border(2.dp, Color.Red)
    )
}
