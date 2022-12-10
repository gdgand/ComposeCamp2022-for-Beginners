package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.R
import com.example.mycity.data.Datasource
import com.example.mycity.model.Category
import com.example.mycity.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class MyCityUiState<T1, T2>(
    val isShowingCategoryList: Boolean = true,
    val isShowingCategoryDetailList: Boolean = false,
    val isShowingDetailScreen: Boolean = false,
    val categoryList: List<Category> = emptyList(),
    val currentCategoryDetailList: List<T1> = emptyList(),
    val currentDetail: T2? = null
)

class MyCityViewModel : ViewModel() {

    private val _uiState by lazy {
        MutableStateFlow(
        MyCityUiState()
    )
    }
    val uiState: StateFlow<MyCityUiState<*, *>> = _uiState

    fun <T> updateCurrentCategoryDetailList(selectedCategory: Category) {

        var currentCategoryDetailList: List<T>

        if (selectedCategory.name == R.string.category_sport) {
            currentCategoryDetailList: List<Sport> = Datasource.SportList
        } else if (selectedCategory.name == R.string.category_dessert){

        } else {

        }

        _uiState.update {
            it.copy(currentCategoryDetailList = Datasource.SportList)
        }
    }

}