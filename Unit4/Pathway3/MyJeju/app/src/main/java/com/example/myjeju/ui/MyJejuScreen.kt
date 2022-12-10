package com.example.myjeju.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myjeju.MainActivity
import com.example.myjeju.R
import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.model.JejuItemContent
import com.example.myjeju.ui.utils.MyJejuContentType

enum class MyJejuScreen(){
    Start,
    CategoryList,
    ItemDetail
}

@Composable
fun MyJejuApp(
    windowSize: WindowWidthSizeClass,
    activity: MainActivity,
    modifier: Modifier = Modifier
){
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyJejuScreen.valueOf(
        backStackEntry?.destination?.route ?: MyJejuScreen.Start.name
    )
    val contentType: MyJejuContentType
    val viewModel: JejuViewModel = viewModel()

    when(windowSize) {
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> {
            contentType = MyJejuContentType.ListAndDetail
        }
        else -> {
            contentType = MyJejuContentType.ListOnly
        }

    }

    Scaffold(
        topBar = {
            JejuAppBar()
        }
    ) {
        innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        if (contentType == MyJejuContentType.ListOnly) {
            NavHost(
                navController = navController,
                startDestination = MyJejuScreen.Start.name,
                modifier = modifier.padding(innerPadding)
            ){
                composable(route = MyJejuScreen.Start.name) {
                    StartScreen(
                        categories = JejuDataProvider.getCategory(),
                        onClick = {
                            viewModel.updateCurrentCategoryItem(it)
                            navController.navigate(MyJejuScreen.CategoryList.name)
                        },
                        onBackPressed = {
                            activity.finish()
                        }
                    )
                }
                composable(route = MyJejuScreen.CategoryList.name) {
                    CategoryScreen(
                        jejuItems = JejuDataProvider.getCategoryData(uiState.currentCategoryItem),
                        onClick = {
                            viewModel.updateCurrentJejuItem(it)
                            navController.navigate(MyJejuScreen.ItemDetail.name)
                        },
                        onBackPressed = {
                            navController.navigate(MyJejuScreen.Start.name)
                        }
                    )
                }
                composable(route = MyJejuScreen.ItemDetail.name) {
                    ItemScreen(
                        selectedJejuItem = uiState.currentJejuItem,
                        onBackPressed = {
                            viewModel.updateCurrentCategoryItem(uiState.currentCategoryItem)
                            navController.navigate(MyJejuScreen.CategoryList.name)
                        }
                    )
                }
            }
        } else {
            MyJejuListAndDetails(
                categories = JejuDataProvider.getCategory(),
                jejuItems = JejuDataProvider.getCategoryData(uiState.currentCategoryItem),
                onCategoryClick = { viewModel.updateCurrentCategoryItem(it) },
                onJejuItemClick = { viewModel.updateCurrentJejuItem(it) },
                selectedJejuItem = uiState.currentJejuItem,
            )
        }
    }
}

@Composable
fun MyJejuListAndDetails (
    categories: List<CategoryItemContent>,
    jejuItems: List<JejuItemContent>,
    onCategoryClick: (CategoryItemContent) -> Unit,
    onJejuItemClick: (JejuItemContent) -> Unit,
    selectedJejuItem: JejuItemContent,
    modifier: Modifier = Modifier
) {
    Row(){
        StartScreen(
            categories = categories,
            onClick = onCategoryClick,
            onBackPressed = {  },
            modifier = Modifier.weight(1f)
        )
        CategoryScreen(
            jejuItems = jejuItems,
            onClick = onJejuItemClick,
            onBackPressed = { },
            modifier = Modifier.weight(1f)
        )
        ItemScreen(
            selectedJejuItem = selectedJejuItem,
            onBackPressed = { },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun JejuAppBar(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 56.dp)
            .padding(top = 16.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h3,
            color = Color.White
        )
        Text(
            text = stringResource(id = R.string.app_desc),
            color = Color.White
        )
    }
}
