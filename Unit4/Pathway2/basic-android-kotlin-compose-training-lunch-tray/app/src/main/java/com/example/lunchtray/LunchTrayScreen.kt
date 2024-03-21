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

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*
import com.example.lunchtray.ui.theme.LunchTrayTheme

private const val TAG = "LunchTrayScreen"

// TODO: Screen enum
enum class LunchTrayScreen {
    Start
    , EntreeMenu
    , SideDishMenu
    , AccomplishmentMenu
    , Checkout
}
// TODO: AppBar
@Composable
fun LunchTrayTopAppBar(canNavigateBack: Boolean
                       , navigateUp: () -> Unit
                       , modifier: Modifier = Modifier
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
    // TODO: Create Controller and initialization
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayTopAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        // TODO: Navigation host
        NavHost(navController = navController
            , startDestination = LunchTrayScreen.Start.name
            , modifier = modifier.padding(innerPadding)) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
//                        viewModel.setQuantity(it)
                        navController.navigate(LunchTrayScreen.EntreeMenu.name)
                    }
                )
            }
            composable(route = LunchTrayScreen.EntreeMenu.name) {
                val context = LocalContext.current
                EntreeMenuScreen(options = DataSource.entreeMenuItems
                    , onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) }
                    , onNextButtonClicked = { navController.navigate(LunchTrayScreen.SideDishMenu.name) }
                    , onSelectionChanged = {
                        viewModel.updateEntree(it)
                    }
                )
            }
            composable(route = LunchTrayScreen.SideDishMenu.name) {
                SideDishMenuScreen(options = DataSource.sideDishMenuItems
                    , onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
                        navController.popBackStack(LunchTrayScreen.EntreeMenu.name, inclusive = false)
                                              }
                    , onNextButtonClicked = { navController.navigate(LunchTrayScreen.AccomplishmentMenu.name) }
                    , onSelectionChanged = {
                        viewModel.updateSideDish(it)
                    }
                )
            }
            composable(route = LunchTrayScreen.AccomplishmentMenu.name) {
                AccompanimentMenuScreen(options = DataSource.accompanimentMenuItems
                    , onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
                        navController.popBackStack(LunchTrayScreen.SideDishMenu.name, inclusive = false)
                                              }
                    , onNextButtonClicked = { navController.navigate(LunchTrayScreen.Checkout.name) }
                    , onSelectionChanged = {
                        viewModel.updateAccompaniment(it)
                    }
                )
            }
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(orderUiState = uiState
                    , onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) }
                    , onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name) }
                )
            }

        }
    }
}


private fun cancelOrderAndNavigateToStart(viewModel: OrderViewModel
                                          , navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LunchTrayAppPreview(){
    LunchTrayTheme {
        LunchTrayApp()
    }
}
