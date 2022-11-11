package hs.project.buseinesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import hs.project.buseinesscardapp.ui.theme.BuseinessCardAppTheme
import hs.project.buseinesscardapp.ui.theme.COLOR_BG
import hs.project.buseinesscardapp.ui.theme.COLOR_DIVIDER
import hs.project.buseinesscardapp.ui.theme.COLOR_GREEN
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuseinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        Modifier
            .background(COLOR_BG)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .weight(3f), contentAlignment = Alignment.Center) {
            BusinessCardMain()
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 20.dp), contentAlignment = Alignment.BottomCenter
        ) {

            Column {
                BusinessCardInfo(
                    painterResource(id = R.drawable.ic_baseline_call),
                    stringResource(id = R.string.str_phone_num)
                )
                BusinessCardInfo(
                    painterResource(id = R.drawable.ic_baseline_share),
                    stringResource(id = R.string.str_android_dev)
                )
                BusinessCardInfo(
                    painterResource(id = R.drawable.ic_baseline_email),
                    stringResource(id = R.string.str_email)
                )
            }
        }

    }
}

@Composable
fun BusinessCardMain() {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            Modifier
                .width(100.dp)
                .height(100.dp)
        )

        Text(
            text = stringResource(id = R.string.str_name),
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp
            ),
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = stringResource(id = R.string.str_main_desc),
            style = TextStyle(
                color = COLOR_GREEN,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(top = 10.dp)
        )
    }

}

@Composable
fun BusinessCardInfo(imagePainter: Painter, text: String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(color = COLOR_DIVIDER, thickness = 1.dp)
        Row {

            Image(
                painter = imagePainter, contentDescription = null,
                colorFilter = ColorFilter.tint(COLOR_GREEN),
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 30.dp, end = 12.dp)
            )
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 10.dp, end = 12.dp)
            )
        }
    }

}

@Preview(
    name = "BusinessCard",
    showBackground = false,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    BuseinessCardAppTheme {
        BusinessCardApp()
    }
}