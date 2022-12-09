package com.example.myjeju.ui

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.ui.theme.MyJejuTheme

@Composable
fun StartScreen(
    categories: List<CategoryItemContent>,
    onClick: (CategoryItemContent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(categories) {
            CategoryItemCard(
                category = it,
                onCategoryClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItemCard(
    category: CategoryItemContent,
    onCategoryClick: (CategoryItemContent) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 2.dp,
        modifier = modifier,
        onClick = { onCategoryClick(category) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
        ) {
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)

            ) {
                Image(
                    painter = painterResource(id = category.iconResourceId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            CircleShape
                        )
                        .padding(4.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = category.categoryResourceId),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemCardPreview() {
    MyJejuTheme() {
        CategoryItemCard(
            category = JejuDataProvider.getCategory()[0],
            onCategoryClick = {}
        )
    }
}