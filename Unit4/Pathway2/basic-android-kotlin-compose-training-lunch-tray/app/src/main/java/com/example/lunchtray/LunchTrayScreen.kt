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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*
import javax.sql.DataSource

enum class Screen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

@Composable
fun LunchTrayAppbar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = currentScreen.title))
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppbar(
                currentScreen = currentScreen,
                canNavigateBack = navHostController.previousBackStackEntry != null,
                navigateUp = navHostController::navigateUp
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navHostController,
            startDestination = Screen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = { navHostController.navigate(Screen.Entree.name) }
                )
            }
            composable(Screen.Entree.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = navHostController::navigateUp,
                    onNextButtonClicked = { navHostController.navigate(Screen.SideDish.name) },
                    onSelectionChanged = viewModel::updateEntree
                )
            }
            composable(Screen.SideDish.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navHostController.popBackStack(Screen.Start.name, false)
                    },
                    onNextButtonClicked = { navHostController.navigate(Screen.Accompaniment.name) },
                    onSelectionChanged = viewModel::updateSideDish
                )
            }
            composable(Screen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navHostController.popBackStack(Screen.Start.name, false)
                    },
                    onNextButtonClicked = { navHostController.navigate(Screen.Checkout.name) },
                    onSelectionChanged = viewModel::updateAccompaniment
                )
            }
            composable(Screen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navHostController.popBackStack(Screen.Start.name, false)
                    },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navHostController.popBackStack(Screen.Start.name, false)
                    })
            }
        }
    }
}
