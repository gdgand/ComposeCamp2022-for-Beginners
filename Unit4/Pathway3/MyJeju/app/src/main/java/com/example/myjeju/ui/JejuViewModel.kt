package com.example.myjeju.ui

import androidx.lifecycle.ViewModel
import com.example.myjeju.data.JejuDataProvider
import com.example.myjeju.model.CategoryItemContent
import com.example.myjeju.model.JejuItemContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class JejuViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        JejuUiState(
            jejuItemList = JejuDataProvider.getJejuData(),
            currentJejuItem = JejuDataProvider.getJejuData().getOrElse(0) {
                JejuDataProvider.defaultJejuData
            },
            categoryItemList = JejuDataProvider.getCategory(),
            currentCategoryItem = JejuDataProvider.getCategory().getOrElse(0) {
                JejuDataProvider.getCategory()[0]
            }
        )
    )
    val uiState: StateFlow<JejuUiState> = _uiState

    fun updateCurrentJejuItem(selectedJejuItem: JejuItemContent) {
       _uiState.update {
           it.copy(currentJejuItem = selectedJejuItem)
       }
    }

    fun updateCurrentCategoryItem(selectedCategoryItem: CategoryItemContent) {
        _uiState.update {
            it.copy(currentCategoryItem = selectedCategoryItem)
        }
    }

    fun navigateToCategoryScreen(){

    }

//    fun navigateToListPage() {
//        _uiState.update {
//            it.copy(isShowingListPage = true)
//        }
//    }

//    fun navigateToJejuItemListPage() {
//        _uiState.update {
//            it.copy(jejuItemList = JejuDataProvider.getCategoryData(it.currentCategoryItem.categoryResourceId))
//        }
//    }

//    fun navigateToDetailPage(){
//        _uiState.update {
//            it.copy(isShowingListPage = false)
//        }
//    }

}