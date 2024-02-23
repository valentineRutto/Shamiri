package com.valentinerutto.shamiri.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valentinerutto.shamiri.data.ResidentLocationItem


@Composable
fun MainView(
    uiState: LocationUiState,
    onItemSelected: (newsItemPosition: Int) -> Unit,
) {

    Column(modifier = Modifier.fillMaxSize()) {

        if (uiState.loading) {
            LoadingView()
        }

        if (uiState.error.isNullOrBlank().not()) {
        }

        if (!uiState.locationItem.isNullOrEmpty()) {

        }
    }

}

@Composable
fun LocationListScreen(
    modifier: Modifier = Modifier,
    itemUIState: LocationUiState
) {
    if (itemUIState.loading) {
        LoadingView()
    }

    if (itemUIState.error.isNullOrBlank().not()) {
        ErrorScreen(modifier = Modifier.padding(4.dp), errorMsg = itemUIState.error)
    }

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .clickable {

        }) {
        itemsIndexed(itemUIState.locationItem) { index, item ->
            CharacterItem(modifier = modifier, character = item)
        }
    }
}

@Composable
fun LoadingView() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
fun CharacterItem(
    modifier: Modifier,
    character: ResidentLocationItem
) {
    Row(modifier = modifier) {
        ImageComposable(imageUrl = character.characterImage, modifier = modifier)
        Text(text = character.characterName)
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier, errorMsg: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = errorMsg,
            textAlign = TextAlign.Center,
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
