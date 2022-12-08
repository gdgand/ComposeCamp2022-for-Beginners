package com.example.myjeju.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.model.JejuItemContent

@Composable
fun CategoryScreen(
    jejuItems: List<JejuItemContent>,
    onClick: (JejuItemContent) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler() {
        onBackPressed()
    }
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(jejuItems, key = { jejuItem -> jejuItem.id }) {
                jejuItem ->
            JejuItemCard(
                jejuItem = jejuItem,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun JejuItemCard(
    jejuItem: JejuItemContent,
    onItemClick: (JejuItemContent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 2.dp,
        modifier = modifier,
        onClick = { onItemClick(jejuItem) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = jejuItem.titleResourceId),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

//@Composable
//fun CategoryList (
//    categories: List<CategoryItemContent>,
//    onClick: (CategoryItemContent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = modifier
//    ) {
//        items(categories) {
//            CategoryItemCard(
//                category = it,
//                onCategoryClick = onClick
//            )
//        }
//    }
//
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun CategoryItemCard(
//    category: CategoryItemContent,
//    onCategoryClick: (CategoryItemContent) -> Unit,
//    modifier: Modifier = Modifier
//){
//    Card(
//        elevation = 2.dp,
//        modifier = modifier,
//        onClick = { onCategoryClick(category) }
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
//                    text = stringResource(id = category.categoryResourceId),
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//        }
//    }
//}