package com.example.mycity

import android.provider.ContactsContract
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.ui.Modifier
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
//                ReplyListAndDetailContent(
//                    replyUiState = replyUiState,
//                    onEmailCardPressed = onEmailCardPressed,
//                    modifier = Modifier.weight(1f),
//                )
            } else {
                CityListOnlyContent(
                    replyUiState = uiState,
                    onPlaceCardPressed = onPlaceCardPressed,
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
fun CityListOnlyContent(
    replyUiState: CityUiState,
    onPlaceCardPressed: (PlaceType) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = replyUiState.selectedCategoryPlaces

     LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            CityHomeTopBar(modifier = Modifier.fillMaxWidth())
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
private fun CityPlaceItemHeader(place: PlaceType, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        MyCityProfileImage(
            drawableResource = R.drawable.ic_baseline_location_city_24,  // TODO: 각자 자기꺼
            description = "CityProfileImage", // email.sender.fullName, // TODO:
            modifier = Modifier.size(40.dp)
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
