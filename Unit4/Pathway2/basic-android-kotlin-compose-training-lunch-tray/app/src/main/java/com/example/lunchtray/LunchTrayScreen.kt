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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*

enum class LaunchTrayScreen(@StringRes val title: Int) {
    START(R.string.app_name),
    CHOOSE_ENTREE(R.string.choose_entree),
    CHOOSE_SIDE_DISH(R.string.choose_side_dish),
    ORDER_CHECKOUT(R.string.order_checkout),
    CHOOSE_ACCOMPANIMENT(R.string.choose_accompaniment)
}

@Composable
fun LaunchTrayAppBar(
    canNivigateBack: Boolean,
    currentScreenTitle: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreenTitle) },
        modifier = modifier,
        navigationIcon = {
            if (canNivigateBack) {
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

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            val titleRes = LaunchTrayScreen.valueOf(
                backStackEntry?.destination?.route ?: LaunchTrayScreen.START.name
            ).title

            LaunchTrayAppBar(
                canNivigateBack = navController.previousBackStackEntry != null,
                currentScreenTitle = stringResource(titleRes),
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LaunchTrayScreen.START.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LaunchTrayScreen.START.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = { navController.navigate(LaunchTrayScreen.CHOOSE_ENTREE.name) }
                )
            }
            composable(route = LaunchTrayScreen.CHOOSE_ENTREE.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    },
                    onNextButtonClicked = { navController.navigate(LaunchTrayScreen.CHOOSE_SIDE_DISH.name) },
                    onSelectionChanged = { viewModel.updateEntree(it) }
                )
            }
            composable(route = LaunchTrayScreen.CHOOSE_SIDE_DISH.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    },
                    onNextButtonClicked = { navController.navigate(LaunchTrayScreen.CHOOSE_ACCOMPANIMENT.name) },
                    onSelectionChanged = { viewModel.updateSideDish(it) }
                )
            }
            composable(route = LaunchTrayScreen.CHOOSE_ACCOMPANIMENT.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    },
                    onNextButtonClicked = { navController.navigate(LaunchTrayScreen.ORDER_CHECKOUT.name) },
                    onSelectionChanged = { viewModel.updateAccompaniment(it) }
                )
            }
            composable(route = LaunchTrayScreen.ORDER_CHECKOUT.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(
                            viewModel,
                            navController
                        )
                    })
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavController
) {
    viewModel.resetOrder()
    navController.popBackStack(LaunchTrayScreen.START.name, inclusive = false)
}
