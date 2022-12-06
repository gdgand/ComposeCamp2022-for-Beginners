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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.ui.*

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.start_order),
    Mainmenu(title = R.string.choose_entree),
    Sidemenu(title = R.string.choose_side_dish),
    Dessertmenu(title = R.string.choose_accompaniment),
    Caculate(title = R.string.order_summary)

}

@Composable
fun LunchTrayAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
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
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Mainmenu.name)
                    }
                )
            }
            composable(route = LunchTrayScreen.Mainmenu.name) {
                val context = LocalContext.current
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Sidemenu.name)
                    },
                    onSelectionChanged = { item -> viewModel.updateEntree(item) }
                )
            }
            composable(route = LunchTrayScreen.Sidemenu.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Dessertmenu.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateSideDish(item)
                    }
                )
            }
            composable(route = LunchTrayScreen.Dessertmenu.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Caculate.name) },
                    onSelectionChanged = { item -> viewModel.updateAccompaniment(item) }
                )
            }
            composable(route = LunchTrayScreen.Caculate.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                )
            }
        }
    }
}
