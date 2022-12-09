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
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val titleResId: Int) {
    Start(R.string.start_order),
    Entree(R.string.choose_entree),
    SideDish(R.string.choose_side_dish),
    Accompaniment(R.string.choose_accompaniment),
    OrderCheckout(R.string.order_checkout)
}

// TODO: AppBar
@Composable
fun AppBar(
    titleResId: Int,
    canNavigateUp: Boolean = false,
    onClickNavigateUp: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = titleResId)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onClickNavigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.back_button))
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // TODO: Create Controller and initialization
    // 파라미터에 선언

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen =
        LunchTrayScreen.valueOf(backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name)
    val canNavigateUp = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            AppBar(
                currentScreen.titleResId,
                canNavigateUp,
                onClickNavigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(
                route = LunchTrayScreen.Start.name
            ) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(LunchTrayScreen.Entree.name)
                })
            }
            composable(
                route = LunchTrayScreen.Entree.name
            ) {
                EntreeMenuScreen(options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SideDish.name)
                    },
                    onSelectionChanged = { viewModel.updateEntree(it) })
            }
            composable(
                route = LunchTrayScreen.SideDish.name
            ) {
                SideDishMenuScreen(options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Accompaniment.name)
                    },
                    onSelectionChanged = { viewModel.updateSideDish(it) })
            }

            composable(
                route = LunchTrayScreen.Accompaniment.name
            ) {
                AccompanimentMenuScreen(options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },

                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.OrderCheckout.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateAccompaniment(it)
                    })
            }

            composable(route = LunchTrayScreen.OrderCheckout.name) {
                CheckoutScreen(orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    }
                )
            }
        }
    }
}