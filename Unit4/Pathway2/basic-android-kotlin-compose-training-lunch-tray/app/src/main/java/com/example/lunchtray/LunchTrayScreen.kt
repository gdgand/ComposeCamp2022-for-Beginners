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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

enum class LunchTrayScreen() {
  Start,
  Entree_Menu,
  Side_Dish_Menu,
  Accompaniment_Menu,
  Checkout,
}

@Composable
fun LunchTrayAppBar(
  currentScreen: String,
  canNavigateBack: Boolean,
  navigateUp: () -> Unit,
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
            contentDescription = stringResource(id = R.string.back_button)
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
  val viewModel: OrderViewModel = viewModel()

  Scaffold(
    topBar = {
      LunchTrayAppBar(
        currentScreen = backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name,
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.navigateUp() }
      )
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
            navController.navigate(LunchTrayScreen.Entree_Menu.name)
          },
        )
      }
      composable(route = LunchTrayScreen.Entree_Menu.name) {
        EntreeMenuScreen(
          options = DataSource.entreeMenuItems,
          onCancelButtonClicked = {
            cancelOrderAndNavigateToStart(viewModel, navController)
          },
          onNextButtonClicked = {
            navController.navigate(LunchTrayScreen.Side_Dish_Menu.name)
          },
          onSelectionChanged = {
            viewModel.updateEntree(it)
          },
        )
      }
      composable(route = LunchTrayScreen.Side_Dish_Menu.name) {
        SideDishMenuScreen(
          options = DataSource.sideDishMenuItems,
          onCancelButtonClicked = {
            cancelOrderAndNavigateToStart(viewModel, navController)
          },
          onNextButtonClicked = {
            navController.navigate(LunchTrayScreen.Accompaniment_Menu.name)
          },
          onSelectionChanged = {
            viewModel.updateSideDish(it)
          },
        )
      }
      composable(route = LunchTrayScreen.Accompaniment_Menu.name) {
        AccompanimentMenuScreen(
          options = DataSource.accompanimentMenuItems,
          onCancelButtonClicked = {
            cancelOrderAndNavigateToStart(viewModel, navController)
          },
          onNextButtonClicked = {
            navController.navigate(LunchTrayScreen.Checkout.name)
          },
          onSelectionChanged = {
            viewModel.updateAccompaniment(it)
          },
        )
      }
      composable(route = LunchTrayScreen.Checkout.name) {
        CheckoutScreen(
          orderUiState = uiState,
          onCancelButtonClicked = {
            cancelOrderAndNavigateToStart(viewModel, navController)
          },
          onNextButtonClicked = {
            cancelOrderAndNavigateToStart(viewModel, navController)
          },
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
  navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}
