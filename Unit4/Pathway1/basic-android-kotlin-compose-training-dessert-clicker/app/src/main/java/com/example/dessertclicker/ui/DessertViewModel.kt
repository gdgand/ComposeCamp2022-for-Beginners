import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.model.Dessert
import com.example.dessertclicker.ui.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.update

class DessertViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState:StateFlow<DessertUiState> = _uiState.asStateFlow()
    var currentDessertImageId:Int = 0
    var currentDessertPrice:Int = 0
    val desserts:List<Dessert> = dessertList
    init{
        currentDessertImageId = desserts[0].imageId
        currentDessertPrice = desserts[0].price
    }

    fun updateUiState() {

        // Update the revenue
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue.plus(currentDessertPrice),
                dessertsSold = currentState.dessertsSold.inc()
            )
        }

        // Show the next dessert
        val dessertToShow = determineDessertToShow()
        currentDessertImageId = dessertToShow.imageId
        currentDessertPrice = dessertToShow.price
    }

    fun determineDessertToShow(
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (_uiState.value.dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
    /*
    var currentDessertPrice by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].price)
    }
    var currentDessertImageId by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].imageId)
    }
     */
}