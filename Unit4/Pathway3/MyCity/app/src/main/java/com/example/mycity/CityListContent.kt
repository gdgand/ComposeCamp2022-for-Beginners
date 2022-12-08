package com.example.mycity

import android.app.Activity
import android.provider.ContactsContract
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.NavigationItemContent
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlaceType
import com.example.mycity.data.local.Datasource
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.util.CityContentType
import com.example.mycity.util.CityNavigationType


@Composable
fun CityAppContent(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    uiState: CityUiState,
    onTabPressed: ((PlaceCategory) -> Unit),
    onPlaceCardPressed: (PlaceType) -> Unit,
    onListBackPressed: ()->Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == CityNavigationType.NAVIGATION_RAIL) {
//            val navigationRailContentDescription = stringResource(R.string.navigation_rail)
            CityNavigationRail(
                currentTab = uiState.currentCategory,
                onTabPressed = onTabPressed,
//                modifier = Modifier.testTag(navigationRailContentDescription)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == CityContentType.LIST_AND_DETAIL) {
                CityListAndDetailContent(
                    uiState = uiState,
                    onPlaceCardPressed = onPlaceCardPressed,
                    modifier = Modifier.weight(1f),
                )
            } else {
                CityListOnlyContent(
                    uiState = uiState,
                    onPlaceCardPressed = onPlaceCardPressed,
                    onBackPressed = onListBackPressed,
                    modifier = Modifier.weight(1f)
                )
            }
            AnimatedVisibility(visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION) {
//                val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
                CityBottomNavigationBar(
                    currentTab = uiState.currentCategory,
                    onTabPressed = onTabPressed,
//                    modifier = Modifier.testTag(bottomNavigationContentDescription)
                )
            }
        }
    }
}


@Composable
fun CityListAndDetailContent(
    uiState: CityUiState,
    onPlaceCardPressed: (PlaceType) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = uiState.selectedCategoryPlaces
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 20.dp)
        ) {
            items(places, key = { email -> email.id }) { place ->
                CityPlaceListItem(
                    place = place,
                    onCardClick = { onPlaceCardPressed(place) }
                )
            }
        }
        val activity = LocalContext.current as Activity
        CityDetailsScreen(
            uiState = uiState,
            modifier = Modifier.weight(1f),
            onBackPressed = { activity.finish() }
        )
    }
}



@Composable
fun CityListOnlyContent(
    uiState: CityUiState,
    onPlaceCardPressed: (PlaceType) -> Unit,
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val places = uiState.selectedCategoryPlaces
    BackHandler {
        onBackPressed()
    }
     LazyColumn(modifier = modifier
         .fillMaxSize()
         .background(color = MaterialTheme.colorScheme.inverseOnSurface)
         .padding(horizontal = 16.dp).padding(top = 24.dp)
     ) {
        item {
            CityListTopBar(onBackButtonClicked = onBackPressed
                , uiState = uiState
                , modifier = Modifier.fillMaxWidth())
        }
        items(places) { place ->
//        items(places, key = { place -> place.id  /* optional ?? */}) { place ->
            CityPlaceListItem(
                place = place,
                onCardClick = {
                    onPlaceCardPressed(place)
                }
            )
        }
    }
}


@Composable
private fun CityListTopBar(
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
                text = uiState.currentCategory.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


//@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@ComposableInferredTarget
fun CityPlaceListItem(
    place: PlaceType,
    onCardClick: () -> Unit,

    modifier: Modifier = Modifier
) {

    Card(onClick = onCardClick,
        modifier = modifier.padding(vertical = 4.dp),
        enabled = true,
//        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            CityPlaceItemHeader(place)
            Text(
                text = place.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = place.tel,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Composable
private fun CityPlaceItemHeader(
        place: PlaceType
        , modifier: Modifier = Modifier) {

    Row(modifier = modifier.fillMaxWidth()) {

        MyCityProfileImage(
            drawableResource = when (place.category) {
                PlaceCategory.Restaurant -> R.drawable.ic_outline_restaurant_24
                PlaceCategory.CoffeeShop -> R.drawable.ic_outline_coffee_24
                PlaceCategory.Park -> R.drawable.ic_outline_park_24
                PlaceCategory.ShoppingMall -> R.drawable.ic_outline_shopping_bag_24
            },  // TODO: 각자 자기꺼
            description = "CityProfileImage", // email.sender.fullName, // TODO:
            modifier = Modifier.size(40.dp).fillMaxSize()
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = place.title,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = place.tel + " " + place.address + " " + place.Email,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = place.descriptions,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CityPlaceListItemPreview() {
    MyCityTheme {
        CityPlaceListItem(place = Datasource.places.get(0)
            , onCardClick = {}
        )
    }
}
