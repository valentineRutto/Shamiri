package com.valentinerutto.shamiri.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.valentinerutto.shamiri.data.LocationItem


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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(
    onItemSelected: (itemPosition: Int) -> Unit,
    modifier: Modifier = Modifier,
    itemEntity: List<LocationItem>
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(itemEntity) { index, item ->
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
