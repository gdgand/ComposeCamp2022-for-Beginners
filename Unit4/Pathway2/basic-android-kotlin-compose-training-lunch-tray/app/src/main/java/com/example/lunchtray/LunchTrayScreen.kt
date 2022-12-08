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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*
import javax.sql.DataSource

enum class LunchTrayScreen() {
    StartOrder,
    EntreeMenu,
    SideDishMenu,
    AccompanimentMenu,
    CheckOut
}

@Composable
fun LunchTrayAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {Text(stringResource(id = R.string.app_name))},
        modifier = modifier,
        navigationIcon = {}
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(

            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.StartOrder.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.StartOrder.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.EntreeMenu.name)
                    }
                )
            }

            composable(route = LunchTrayScreen.EntreeMenu.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SideDishMenu.name)                  
                    },
                    onSelectionChanged = {
                        item -> viewModel.updateEntree(item)
                    }
                )
            }

            composable(route = LunchTrayScreen.SideDishMenu.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.AccompanimentMenu.name)                  
                    },
                    onSelectionChanged = {
                        item -> viewModel.updateSideDish(item)
                    }
                )
            }

            composable(route = LunchTrayScreen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
                    },
                    onNextButtonClicked = { 
                        navController.navigate(LunchTrayScreen.CheckOut.name)                  
                    },
                    onSelectionChanged = {
                        item -> viewModel.updateAccompaniment(item)
                    }
                )
            }

            composable(route = LunchTrayScreen.CheckOut.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
                    },
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
                    })
            }
        }
    }
}
