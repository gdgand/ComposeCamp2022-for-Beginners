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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*

enum class LunchTrayScreen(){
    StartOrder,
    EntreeMenu,
    SideDishMenu,
    AccompanimentMenu,
    Checkout
}

@Composable
fun LunchTrayAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    currentScreen: LunchTrayScreen,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.StartOrder.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(navController = navController,
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
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SideDishMenu.name) },
                    onSelectionChanged = {viewModel.updateEntree(it)}
                )

            }

            composable(route = LunchTrayScreen.SideDishMenu.name) {
                SideDishMenuScreen(
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.AccompanimentMenu.name) },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    options = sideDishMenuItems,
                    onSelectionChanged = { viewModel.updateSideDish(it) }
                )
            }

            composable(route = LunchTrayScreen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Checkout.name) },
                    onSelectionChanged = { viewModel.updateAccompaniment(it) }
                )
            }

            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) }
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.StartOrder.name, inclusive = false)
}
