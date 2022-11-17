package com.example.artspace

import android.media.Image
import android.media.MediaDescription
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.core.graphics.component3
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
                    ArtSpaceApp("Android")
                }
            }
        }
    }
}

class ArtPiece(@DrawableRes val image: Int
    , @StringRes val title: Int
    , @StringRes val source:Int
    )

@Composable
fun ArtSpaceApp(name: String) {
    var currentIndex by remember { mutableStateOf(0) }

//    val myChristmasArts = listOf(
//        (R.drawable.image_1, R.string.image_title_1, R.string.image_source_1)
//        , (R.drawable.image_2, R.string.image_title_2, R.string.image_source_2)
//        , (R.drawable.image_3, R.string.image_title_3, R.string.image_source_3)
//        , (R.drawable.image_4, R.string.image_title_4, R.string.image_source_4)
//    )

    val myChristmasArts : Array<ArtPiece> = arrayOf(ArtPiece(R.drawable.image_1, R.string.image_title_1, R.string.image_source_1)
        , ArtPiece(R.drawable.image_2, R.string.image_title_2, R.string.image_source_2)
        , ArtPiece(R.drawable.image_3, R.string.image_title_3, R.string.image_source_3)
        , ArtPiece(R.drawable.image_4, R.string.image_title_4, R.string.image_source_4))

    val artPiece = myChristmasArts.get(currentIndex)
    ArtOnePiece(image = artPiece.image
            , title = artPiece.title
            , imageDescription = artPiece.source
            , onPrevButton = {
                    if (currentIndex > 0) currentIndex--
                    else { currentIndex = myChristmasArts.size - 1 }
                             }
            , onNextButton = {
                    if (currentIndex < myChristmasArts.size - 1) currentIndex++
                    else { currentIndex = 0 }
                }
    )
}



@Composable
fun ArtOnePiece(@DrawableRes image: Int
    , @StringRes imageDescription: Int
    , @StringRes title: Int
    , onPrevButton: ()-> Unit
    , onNextButton: ()-> Unit
) {
    val padding = 20.dp
    val density = LocalDensity.current
    val shadowModifier = Modifier
        .padding(padding)
        .drawWithContent {
            val paddingPx = with(density) { padding.toPx() }
            clipRect(
                left = -paddingPx,
                top = 0f,
                right = size.width + paddingPx,
                bottom = size.height + paddingPx
            ) {
                this@drawWithContent.drawContent()
            }
        }

    Column(modifier = Modifier.fillMaxWidth().padding(32.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
//        , verticalArrangement = Arrangement.Center
    ) {

    // 1. A picture of Art
        Surface( shape = CutCornerShape(8.dp), color = Color.White, elevation = 12.dp
            , modifier = Modifier.weight(7f).fillMaxSize()
            .drawWithContent {
                val paddingPx = with(density) { padding.toPx() }
                clipRect(
                    left = -paddingPx,
                    top = 0f,
                    right = size.width + paddingPx,
                    bottom = size.height + paddingPx
                ) {
                    this@drawWithContent.drawContent()
                }
            }
        ) {

            val gradientBrush = Brush.horizontalGradient(
                colors = listOf(Color.Red, Color.Blue, Color.Green),
                startX = 0.0f,
                endX = 500.0f,
                tileMode = TileMode.Repeated
            )

            Image(painter = painterResource(image)
                , contentDescription = "null"
                , modifier = Modifier.border(BorderStroke(2.dp, Color.LightGray),
                    shape = CutCornerShape(4.dp)
                ).padding(24.dp)
                , contentScale = ContentScale.Crop
            )

        }


        Surface(shape = RectangleShape, color = Color.White, elevation = 12.dp
            , modifier = Modifier
                .padding(padding)
                .drawWithContent {
                    val paddingPx = with(density) { padding.toPx() }
                    clipRect(
                        left = -paddingPx,
                        top = 0f,
                        right = size.width + paddingPx,
                        bottom = size.height + paddingPx
                    ) {
                        this@drawWithContent.drawContent()
                    }
                }
        ) {

            // 2. Title
            Column(
                modifier = Modifier.weight(2f).fillMaxWidth().height(120.dp).padding(12.dp)
                , horizontalAlignment = Alignment.Start
            ) {

                val titleWithStyle = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )
                    ) {
                        append(stringResource(title))
                    }
                }
                Text(text = titleWithStyle, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                val subtitleWithStyle = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    ) {
                        append(stringResource(imageDescription))
                    }
                    append(' ')
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
                        append(stringResource(R.string.image_source_company))
                    }
                }
                Text(text = subtitleWithStyle, fontSize = 16.sp)
            }
        }


        // 3. prev, next buttons
        Row(modifier = Modifier.weight(1f)
            , verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onPrevButton
                , colors = ButtonDefaults.buttonColors(contentColor = Color.Cyan, backgroundColor = Color.Blue)
    //            , enabled = true
    //            , shape = Shape
                , modifier = Modifier.wrapContentSize()
                    .height(40.dp).width(100.dp)
            ) {
                Text(stringResource(R.string.button_prev))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onNextButton
                , colors = ButtonDefaults.buttonColors(contentColor = Color.Cyan, backgroundColor = Color.Blue)
    //            , enabled = true
    //            , shape = Shape
                , modifier = Modifier.wrapContentSize()
                    .height(40.dp).width(100.dp)
            ) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp("Android")
    }
}