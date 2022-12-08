package com.example.myjeju.ui

import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.model.JejuItemContent

data class JejuUiState(
    val jejuItemList: List<JejuItemContent> = emptyList(),
    val currentJejuItem: JejuItemContent = JejuDataProvider.defaultJejuData,
    val categoryItemList: List<CategoryItemContent> = emptyList(),
    val currentCategoryItem: CategoryItemContent = JejuDataProvider.getCategory()[0],
    val isShowingListPage: Boolean = true
)
