package com.example.myjeju.ui

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.JejuItemContent

enum class MyJejuScreen(){
    Start,
    CategoryList,
    ItemDetail
}

@Composable
fun MyJejuApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
){
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyJejuScreen.valueOf(
        backStackEntry?.destination?.route ?: MyJejuScreen.Start.name
    )

    val viewModel: JejuViewModel = viewModel()

    when(windowSize) {

    }

    Scaffold(
        topBar = {
            JejuAppBar(

            )
        }
    ) {
        innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
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
                    }
                )
            }
            composable(route = MyJejuScreen.CategoryList.name) {
                CategoryScreen(
                    jejuItems = JejuDataProvider.getCategoryData(uiState.currentCategoryItem.categoryResourceId),
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

//        CategoryList(
//            categories = JejuDataProvider.getCategory(),
//            onClick = {
//                viewModel.updateCurrentCategoryItem(it)
////                viewModel.navigateToListPage()
//                viewModel.navigateToJejuItemListPage()
//            }
//        )


//        if (uiState.isShowingListPage) {
//            JejuItemList(
////                jejuItems = uiState.jejuItemList,
//                jejuItems = JejuDataProvider.getCategoryData(categoryId = R.string.c_market),
//                // 그래 바로 이거야!!!!!
//                onClick = {
//                    viewModel.updateCurrentJejuItem(it)
//                    viewModel.navigateToDetailPage()
//                },
//                modifier = modifier.padding((innerPadding))
//            )
//        } else {
//            JejuItemDetail(
//                selectedJejuItem = uiState.currentJejuItem,
//                modifier = modifier.padding((innerPadding)),
//                onBackPressed = {
//                    viewModel.navigateToListPage()
//                }
//            )
//        }
    }
}

@Composable
fun JejuAppBar(

){
    TopAppBar() {

    }
}

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//private fun JejuItemCard(
//    jejuItem: JejuItemContent,
//    onItemClick: (JejuItemContent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        elevation = 2.dp,
//        modifier = modifier,
//        onClick = { onItemClick(jejuItem) }
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(min = 150.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(top = 16.dp)
//            ) {
//                Text(
//                    text = stringResource(id = jejuItem.titleResourceId),
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//        }
//    }
//}

//@Composable
//fun JejuItemList(
//    jejuItems: List<JejuItemContent>,
//    onClick: (JejuItemContent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = modifier
//    ) {
//        items(jejuItems, key = { jejuItem -> jejuItem.id }) {
//            jejuItem ->
//            JejuItemCard(
//                jejuItem = jejuItem,
//                onItemClick = onClick
//            )
//        }
//    }
//}

//@Composable
//private fun JejuItemDetail(
//    selectedJejuItem: JejuItemContent,
//    onBackPressed: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    BackHandler() {
//        onBackPressed()
//    }
//    Column (
//        modifier = modifier.padding(4.dp)
//    ) {
//        Box {
//            Image(
//                painter = painterResource(id = selectedJejuItem.imageResourceId),
//                contentDescription = null,
//                alignment = Alignment.TopCenter,
//                contentScale = ContentScale.Crop
//            )
//            Text(
//                text = stringResource(id = selectedJejuItem.titleResourceId),
//                modifier = Modifier
//                    .padding(8.dp)
//                    .align(Alignment.BottomStart)
//            )
//        }
//        Text(
//            text = stringResource(id = selectedJejuItem.descriptionResourceId)
//        )
//
//    }
//
//}