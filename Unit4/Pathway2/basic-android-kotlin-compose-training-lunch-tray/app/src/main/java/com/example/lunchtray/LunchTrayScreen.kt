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
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

enum class SCREEN(@StringRes val text: Int) {
    START(R.string.app_name),
    ENTREE(R.string.choose_entree),
    SIDE_DISH(R.string.choose_side_dish),
    ACCOMPANIMENT(R.string.choose_accompaniment),
    CHECKOUT(R.string.order_checkout)
}

@Composable
fun AppBar(
    @StringRes title: Int,
    canGoBack: Boolean,
    onClick: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        modifier = Modifier,
        navigationIcon = {
            if (canGoBack) {
                IconButton(onClick = onClick) {
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
    val controller = rememberNavController()
    val viewModel: OrderViewModel = viewModel()
    val currentBackStackEntry by controller.currentBackStackEntryAsState()
    val nowStep = SCREEN.valueOf(currentBackStackEntry?.destination?.route ?: SCREEN.START.name)

    Scaffold(
        topBar = {
            AppBar(
                title = nowStep.text,
                canGoBack = controller.previousBackStackEntry != null,
                onClick = { controller.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = controller,
            startDestination = SCREEN.START.name,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(route = SCREEN.START.name) {
                StartOrderScreen(onStartOrderButtonClicked = { controller.navigate(SCREEN.ENTREE.name) })
            }

            composable(route = SCREEN.ENTREE.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onNextButtonClicked = { controller.navigate(SCREEN.SIDE_DISH.name) },
                    onSelectionChanged = { data -> viewModel.updateEntree(data) },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        controller.popBackStack(SCREEN.START.name, inclusive = false)
                    }
                )
            }

            composable(route = SCREEN.SIDE_DISH.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onSelectionChanged = { data -> viewModel.updateSideDish(data) },
                    onNextButtonClicked = { controller.navigate(SCREEN.ACCOMPANIMENT.name) },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        controller.popBackStack(SCREEN.START.name, inclusive = false)
                    }
                )
            }

            composable(route = SCREEN.ACCOMPANIMENT.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onNextButtonClicked = { controller.navigate(SCREEN.CHECKOUT.name) },
                    onSelectionChanged = { data -> viewModel.updateAccompaniment(data) },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        controller.popBackStack(SCREEN.START.name, inclusive = false)
                    }
                )
            }

            composable(route = SCREEN.CHECKOUT.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        controller.popBackStack(SCREEN.START.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        controller.popBackStack(SCREEN.START.name, inclusive = false)
                    }
                )
            }
        }
    }
}
