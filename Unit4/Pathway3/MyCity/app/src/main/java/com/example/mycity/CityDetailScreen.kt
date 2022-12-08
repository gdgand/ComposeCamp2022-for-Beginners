package com.example.mycity

import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import com.example.mycity.data.local.Datasource
import com.example.mycity.data.local.Datasource.places
import com.example.mycity.ui.theme.MyCityTheme
import java.nio.file.Files.delete

@Composable
fun CityDetailsScreen(
    uiState: CityUiState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn(
        modifier = modifier
//            .testTag(stringResource(R.string.details_screen))
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = 24.dp)
    ) {
        item {
            if (isFullScreen) {
                CityDetailsScreenTopBar(onBackPressed, uiState)
            }
            CityPlaceDetailsCard(
                place = uiState.selectedPlace!!,
                category = uiState.currentCategory,
                isFullScreen = isFullScreen,
                modifier = if (isFullScreen)
                    Modifier.padding(horizontal = 16.dp)
                else
                    Modifier.padding(end = 16.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityPlaceDetailsCard(
    place: PlaceType,
    category: PlaceCategory,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DetailsScreenHeader(place)
            if (!isFullScreen) {
                Text(
                    text = place.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }
            Text(
                text = place.descriptions,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            DetailsScreenBottomBar(category, displayToast)
        }
    }
}


@Composable
private fun DetailsScreenHeader(place: PlaceType
                                , modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        MyCityProfileImage(
            drawableResource = when (place.category) {
                PlaceCategory.Restaurant -> R.drawable.ic_outline_restaurant_24
                PlaceCategory.CoffeeShop -> R.drawable.ic_outline_coffee_24
                PlaceCategory.Park -> R.drawable.ic_outline_park_24
                PlaceCategory.ShoppingMall -> R.drawable.ic_outline_shopping_bag_24
            }
            , description = "MyCity"
            , modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "detail info"// email.sender.firstName,
                , style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "detail info"//email.createdAt,
                , style = MaterialTheme.typography.labelMedium
                , color = MaterialTheme.colorScheme.outline
            )
        }
    }
}


@Composable
private fun CityDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    uiState: CityUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = uiState.selectedPlace!!.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
private fun DetailsScreenBottomBar(
    category: PlaceCategory,
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (category) {
        PlaceCategory.CoffeeShop ->
            ActionButton(
                text = stringResource(id = R.string.show_menu),
                onButtonClicked = displayToast,
                modifier = modifier
            )
        PlaceCategory.Restaurant ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                ActionButton(
                    text = stringResource(id = R.string.show_vacancy),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = stringResource(id = R.string.dining_revervation),
                    onButtonClicked = displayToast,
                    containIrreversibleAction = true,
                    modifier = Modifier.weight(1f)
                )
            }
        PlaceCategory.Park, PlaceCategory.ShoppingMall ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                ActionButton(
                    text = stringResource(id = R.string.call_to_manager),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = stringResource(id = R.string.vallet_park_reservation),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
            }
    }
}

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false
) {
    Button(
        onClick = { onButtonClicked(text) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.inverseOnSurface
            else MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            text = text,
            color =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.onSurfaceVariant
            else MaterialTheme.colorScheme.onError
        )
    }
}




@Preview(showBackground = true)
@Composable
fun CityDetailScreenPreview() {
    MyCityTheme {

        val uiState = CityUiState(currentCategory = PlaceCategory.Park
            , places = Datasource.places.groupBy { it.category }
            , selectedPlace = Datasource.places.get(0)
        )

        CityDetailsScreen(uiState = uiState
            , onBackPressed = {}
            , isFullScreen = true
        )
    }
}