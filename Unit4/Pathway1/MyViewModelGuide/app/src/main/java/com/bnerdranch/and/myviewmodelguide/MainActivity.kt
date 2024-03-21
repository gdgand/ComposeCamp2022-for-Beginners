package com.bnerdranch.and.myviewmodelguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.bnerdranch.and.myviewmodelguide.model.DiceRollViewModel
import com.bnerdranch.and.myviewmodelguide.ui.theme.MyViewModelGuideTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : DiceRollViewModel by viewModels()

        lifecycleScope.launch {
            repaetOnLifecycle(lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI element
//                    Text(text = numberOfRolls.toString())
                }
            }
        }

//        setContent {
//            MyViewModelGuideTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyViewModelGuideTheme {
        Greeting("Android")
    }
}