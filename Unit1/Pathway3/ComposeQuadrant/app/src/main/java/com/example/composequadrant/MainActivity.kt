package com.example.composequadrant
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

//하나의 사분면
@Composable
fun Card(title:String,
         desc:String,
         color:Color,
         modifier: Modifier=Modifier ){
    Column(
        modifier= modifier
            .padding(16.dp)
            .background(color)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(bottom=16.dp)

        )
        Text(
            text=desc,
            textAlign=TextAlign.Justify
        )

    }

}
@Composable
fun App(){

    Column(
        Modifier.fillMaxWidth()

    ){
        Row(Modifier.weight(1f)){
            Card(title= stringResource(id = R.string.second_title),
                desc= stringResource(id = R.string.second_description),
                color = Color.Green,
                modifier = Modifier.weight(1f))
            Card(
                title= stringResource(id = R.string.first_title),
                desc= stringResource(id = R.string.first_description),
                color = Color.Yellow,
                modifier = Modifier.weight(1f)
            )


        }
        Row(Modifier.weight(1f)){
            Card(title= stringResource(id = R.string.third_title),
                desc= stringResource(id = R.string.third_description),
                color = Color.Blue,
                modifier = Modifier.weight(1f))
            Card(title= stringResource(id = R.string.fourth_title),
                desc= stringResource(id = R.string.fourth_description),
                color = Color.LightGray,
                modifier = Modifier.weight(1f)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        App()
    }
}