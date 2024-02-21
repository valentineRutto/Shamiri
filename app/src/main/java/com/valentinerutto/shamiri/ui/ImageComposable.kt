package com.valentinerutto.shamiri.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage


@Composable
fun ImageComposable(imageUrl: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        alignment = Alignment.CenterStart,
        model = imageUrl,
        contentDescription = "",
    )
}