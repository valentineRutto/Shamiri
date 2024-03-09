package com.valentinerutto.shamiri.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.valentinerutto.shamiri.data.local.CharacterEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<CharacterEntity>,
    onSearchQueryChange: (String) -> Unit
) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = "Search Character")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        content = {},
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    )
}