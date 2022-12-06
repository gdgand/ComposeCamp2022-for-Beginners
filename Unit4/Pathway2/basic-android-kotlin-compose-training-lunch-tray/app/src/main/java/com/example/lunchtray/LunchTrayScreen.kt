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
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.StartOrderPreview
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.start_order),
    Mainmenu(title = R.string.choose_entree),
    Sidemenu(title = R.string.choose_side_dish),
    Dessertmenu(title = R.string.choose_accompaniment),
    caculate(title = R.string.order_summary)

}

// TODO: AppBar

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            // TODO: AppBar
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Mainmenu.name)
                    }
                )
            }
            composable(route = LunchTrayScreen.Mainmenu.name) {
                val context = LocalContext.current
            }
            composable(route = LunchTrayScreen.Sidemenu.name) {

            }
            composable(route = LunchTrayScreen.Dessertmenu.name) {

            }
            composable(route = LunchTrayScreen.caculate.name) {

            }
         }
    }
}
