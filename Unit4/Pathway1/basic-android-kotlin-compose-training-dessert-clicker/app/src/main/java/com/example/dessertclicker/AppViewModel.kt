package com.example.dessertclicker

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

//    fun updateDessertStatus(currentDessertIndex: Int, revenue: Int, n_desserts: Int) {
//    와 이게 내 생각대로 동작하는게 아니네~~~~ ㅠㅠ
//        _uiState.update { currentState ->
//            currentState.copy(
//                revenue = revenue + dessertList[currentDessertIndex].price,
//                dessertsSold = n_desserts + 1,
//                currentDessertIndex = idx + 1
//            )
//        }
//    }

    fun updateDessertStatus() {
        _uiState.update { currentState ->
            val r = currentState.revenue
            val currentIdx = currentState.currentDessertIndex
            val sold = currentState.dessertsSold + 1
            Log.d("appviewmodel", "currentDessertIndex : " + currentIdx)
            if (sold > dessertList[currentIdx].startProductionAmount) {
                currentState.copy(
                    currentDessertIndex = currentIdx + 1,
                    revenue = r + dessertList[currentIdx + 1].price,
                    dessertsSold = sold,
                    currentDessertImageId = dessertList[currentIdx + 1].imageId,
                    currentDessertPrice = dessertList[currentIdx + 1].price,
                )
            } else {
                currentState.copy(
                    revenue = r + dessertList[currentIdx].price,
                    dessertsSold = sold,
                    currentDessertImageId = dessertList[currentIdx].imageId,
                    currentDessertPrice = dessertList[currentIdx].price,
                )
            }
        }
    }
}