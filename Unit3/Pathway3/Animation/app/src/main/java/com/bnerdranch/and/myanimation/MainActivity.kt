package com.bnerdranch.and.myanimation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bnerdranch.and.myanimation.ui.theme.MyAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var visible by remember { mutableStateOf(true) }

//    val state = remember {
//        MutableTransitionState(false).apply {  targetState = true }
//    }
    val density = LocalDensity.current

    Box {
        AnimatedVisibility(
            visible = visible
            //        visibleState = state
            //        , enter = slideInVertically {
            //            // Slide in from 40 dp from the top.
            //            with(density) { -40.dp.roundToPx() }
            //        } + expandVertically(
            //            // Expand from the top.
            //            expandFrom = Alignment.Top
            //        ) + fadeIn(
            //            // Fade in with the initial alpha of 0.3f.
            //            initialAlpha = 0.3f
            //        )
            //        , exit = slideOutVertically() + shrinkVertically() + fadeOut()
            , exit = fadeOut()
        ) {

            //        Column() {
            Text(text = "Edit")
            //            Text(text = "Hello $name!")
            //            Text("Hello", Modifier.fillMaxWidth().height(200.dp))
            //            Text(
            //                text = when {
            //                    state.isIdle && state.currentState -> "Visible"
            //                    !state.isIdle && state.currentState -> "Disappearing"
            //                    state.isIdle && !state.currentState -> "Invisible"
            //                    else -> "Appearing"
            //                }
            //            )
            //        }
        }
    }

}

//@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAnimationTheme {
        Greeting("Android")
    }
}