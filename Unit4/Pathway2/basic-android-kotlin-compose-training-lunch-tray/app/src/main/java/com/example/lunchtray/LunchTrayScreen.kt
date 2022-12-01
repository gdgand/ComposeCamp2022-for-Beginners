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

import android.content.Context
import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.ui.*

enum class LunchTrayScreen(@StringRes val title: Int) {
    START(R.string.start_order),
    ENTREE(R.string.choose_entree),
    SIDE(R.string.choose_side_dish),
    ACCOMPANIMENT(R.string.choose_accompaniment),
    CHECKOUT(R.string.order_checkout)
}

@Composable
fun LunchAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.back_button))
                }
            }
        },
    )
}

@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: OrderViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.START.name
    )

    Scaffold(
        topBar = {
            LunchAppBar(currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(navController = navController,
            startDestination = LunchTrayScreen.START.name,
            modifier = modifier.padding(innerPadding)) {

            composable(route = LunchTrayScreen.START.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(LunchTrayScreen.ENTREE.name)
                })
            }

            composable(route = LunchTrayScreen.ENTREE.name) {
                EntreeMenuScreen(options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { onCancel(navController, viewModel) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.ACCOMPANIMENT.name) },
                    onSelectionChanged = { viewModel.updateEntree(it) })
            }

            composable(route = LunchTrayScreen.ACCOMPANIMENT.name) {
                AccompanimentMenuScreen(options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { onCancel(navController, viewModel) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SIDE.name) },
                    onSelectionChanged = {viewModel.updateAccompaniment(it)})
            }

            composable(route = LunchTrayScreen.SIDE.name) {
                SideDishMenuScreen(options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { onCancel(navController, viewModel) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.CHECKOUT.name) },
                    onSelectionChanged = {viewModel.updateSideDish(it)})
            }

            composable(route = LunchTrayScreen.CHECKOUT.name) {
                CheckoutScreen(orderUiState = uiState,
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.START.name) },
                    onCancelButtonClicked = { onCancel(navController, viewModel) })
            }
        }
    }
}

private fun onCancel(
    navController: NavHostController,
    viewModel: OrderViewModel,
) {
    viewModel.resetOrder()
    navController.popBackStack(route = LunchTrayScreen.START.name, inclusive = false)
}
