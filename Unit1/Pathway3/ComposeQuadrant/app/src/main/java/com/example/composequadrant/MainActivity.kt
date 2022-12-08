package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantApp()
        }
    }
}

@Composable
fun ComposeQuadrantApp() {

//    val modifier = Modifier

    Column(modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .weight(1f)
           ,  horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            ComposableInfoCard(stringResource(R.string.first_title)
                , stringResource(R.string.first_description)
                , backgroundColor = Color.Green
                , modifier = Modifier.weight(1f)
            )

            ComposableInfoCard(stringResource(R.string.second_title)
                , stringResource(R.string.second_description)
                , backgroundColor = Color.Yellow
                , modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.weight(1f)
            ,  horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            ComposableInfoCard(stringResource(R.string.third_title)
                , stringResource(R.string.third_description)
                , backgroundColor = Color.Cyan
                , modifier = Modifier.weight(1f)
            )


            ComposableInfoCard(stringResource(R.string.fourth_title)
                , stringResource(R.string.fourth_description)
                , backgroundColor = Color.LightGray
                , modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
//    Box(modifier = modifier
//        .background(color = backgroundColor).fillMaxSize()
//    ) {
        Column(modifier = modifier.fillMaxSize().background(color = backgroundColor).padding(16.dp)
        , verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = title
                , fontWeight = FontWeight.Bold
                , modifier = Modifier.padding(4.dp)
            )

            Text(text = description
                , textAlign = Justify
            )
        }
//    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantApp()
}