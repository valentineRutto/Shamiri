package com.valentinerutto.shamiri.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.valentinerutto.shamiri.data.ResidentLocationItem
import com.valentinerutto.shamiri.data.remote.Result
import org.koin.androidx.compose.koinViewModel


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
    modifier: Modifier = Modifier, viewModel: LocationViewmodel = koinViewModel()
) {
    val locations = viewModel.pagedLocationResults.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .clickable {
        }) {
        items(locations.itemCount) { item ->
            LocationItem(modifier = modifier, location = locations[item]!!)

        }
        locations.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //   LoadingView()

                }

                loadState.append is LoadState.NotLoading -> {
                    //You can add modifier to manage load state when next response page is loading
                }

                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
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
    modifier: Modifier, character: ResidentLocationItem
) {
    Row(modifier = modifier) {
        ImageComposable(imageUrl = character.characterImage, modifier = modifier)
        Text(text = character.characterName)
    }
}

@Composable
fun LocationItem(
    modifier: Modifier, location: Result
) {
    Row(modifier = modifier) {
        location.url?.let { ImageComposable(imageUrl = it, modifier = modifier) }
        location.name?.let { Text(text = it) }
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
