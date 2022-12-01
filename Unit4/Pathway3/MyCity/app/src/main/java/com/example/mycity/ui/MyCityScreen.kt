package com.example.mycity.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.Datasource
import com.example.mycity.model.Category
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.R
import com.example.mycity.model.Dessert
import com.example.mycity.model.Sport

@Composable
fun MyCityHomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            MyCityAppBar(
            )
        }
    ) { innerPadding ->
        MyCityCategoryList(
            categoryList = Datasource.categoryList,
            onClick = {},
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MyCityAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_name)
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_button)
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun MyCityCategoryList(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    onClick: (Category) -> Unit,
) {
    LazyColumn {
        items(categoryList) { category ->
            MyCityCategoryItem(
                category = category,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCityCategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onItemClick: (Category) -> Unit,
) {
    Card(
        elevation = 4.dp,
        modifier = modifier,
        onClick = { onItemClick(category) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = category.imageId),
                contentDescription = null,
                modifier = Modifier.width(80.dp)
            )
            Text(
                text = stringResource(id = category.name),
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun <T> CategoryDetailList(
    modifier: Modifier = Modifier,
    categoryDetailList: List<T>,
    onClick: () -> Unit,
) {
    LazyColumn {
        items(categoryDetailList) { categoryDetail ->
            CategoryDetailItem(
                selectedCategory = categoryDetail,
                onItemClick = onClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> CategoryDetailItem(
    modifier: Modifier = Modifier,
    selectedCategory: T,
    onItemClick: () -> Unit,
) {

    @DrawableRes
    val imageId: Int

    @StringRes
    val stringId: Int

    when (selectedCategory) {
        is Dessert -> {
            imageId = selectedCategory.imageId
            stringId = selectedCategory.name
        }
        is Sport -> {
            imageId = selectedCategory.imageResourceId
            stringId = selectedCategory.titleResourceId
        }
        // 이거 이렇게 쓰는게 더 문제인데ㅜ 어떻게 해야할지 모르겠다.
        // 0으로 초기화 하지말고, 그냥. selectedCategory를 강제로 캐스팅해서 빈더미를 넣어줘야겠다.
        // val이라서 재할당도 안되고..;; 모르겠다..
        else -> {
            selectedCategory as Dessert
            imageId = selectedCategory.imageId
            stringId = selectedCategory.name
        }
    }

    Card(
        elevation = 4.dp,
        modifier = modifier,
        onClick = { onItemClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 80.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.width(80.dp)
            )
            Text(
                text = stringResource(id = stringId),
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun <T> DetailScreen(
    modifier: Modifier = Modifier,
    selectedDetail: T,
) {
    @DrawableRes
    val imageId: Int

    @StringRes
    val descriptionStringId: Int

    when (selectedDetail) {
        is Dessert -> {
            imageId = selectedDetail.imageId
            descriptionStringId = selectedDetail.description
        }
        is Sport -> {
            imageId = selectedDetail.imageResourceId
            descriptionStringId = selectedDetail.newsDetails
        }
        // 이거 이렇게 쓰는게 더 문제인데ㅜ 어떻게 해야할지 모르겠다.
        else -> {
            selectedDetail as Dessert
            imageId = selectedDetail.imageId
            descriptionStringId = selectedDetail.description
        }
    }

    Column() {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 150.dp)
        )

        Text(
            text = stringResource(id = descriptionStringId),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MyCityTheme {
        DetailScreen(
            selectedDetail = Sport(
                id = 1,
                titleResourceId = R.string.baseball,
                imageResourceId = R.drawable.ic_baseball_square,
                newsDetails = R.string.sports_news_detail_text
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDetailListPreview() {
    MyCityTheme {
        CategoryDetailList(
            //categoryDetailList = Datasource.dessertList,
            categoryDetailList = Datasource.SportList,
            onClick = {},
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryDetailItemPreview() {
    MyCityTheme {
        CategoryDetailItem(
            selectedCategory = Dessert(R.drawable.cupcake, R.string.cupcake, 5, R.string.dessert_description),
            onItemClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityHomeScreenPreview() {
    MyCityTheme {
        MyCityHomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityCategoryListPreview() {
    MyCityTheme {
        MyCityCategoryList(categoryList = Datasource.categoryList, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityCategoryItemPreview() {
    MyCityTheme {
        // MyCityHomeScreen()
        MyCityCategoryItem(
            category = Category(R.drawable.cupcake, R.string.category_dessert),
            onItemClick = {}
        )
    }
}