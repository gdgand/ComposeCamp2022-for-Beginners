package com.example.dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update {
            cupcakeUiState ->
            val dessertsSold = cupcakeUiState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            cupcakeUiState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = cupcakeUiState.revenue + cupcakeUiState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
                currentDessertPrice = dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices){
            if(dessertsSold >= dessertList[index].startProductionAmount){
                dessertIndex = index
            }
            else {
                break
            }
        }
        return dessertIndex
    }
//    잘 모르겠어서 솔루션 참고

//    val currentDessertIndex: Int = 0

//    /**
//     * Determine which dessert to show.
//     */
//    fun determineDessertToShow(
//        dessertsSold: Int
//    ): Dessert {
//        var dessertToShow = desserts.first()
//        for (dessert in desserts) {
//            if (dessertsSold >= dessert.startProductionAmount) {
//                dessertToShow = dessert
//            } else {
//                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
//                // you'll start producing more expensive desserts as determined by startProductionAmount
//                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
//                // than the amount sold.
//                break
//            }
//        }
//
//        return dessertToShow
//    }
//
//    /**
//     * Share desserts sold information using ACTION_SEND intent
//     */
//    private fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
//        val sendIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(
//                Intent.EXTRA_TEXT,
//                intentContext.getString(R.string.share_text, dessertsSold, revenue)
//            )
//            type = "text/plain"
//        }
//
//        val shareIntent = Intent.createChooser(sendIntent, null)
//
//        try {
//            ContextCompat.startActivity(intentContext, shareIntent, null)
//        } catch (e: ActivityNotFoundException) {
//            Toast.makeText(
//                intentContext,
//                intentContext.getString(R.string.sharing_not_available),
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
}