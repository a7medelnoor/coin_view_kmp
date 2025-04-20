package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.a7medelnoor.coin_view_kmp.util.Dimensions

import com.a7medelnoor.coin_view_kmp.util.StringResources.EMPTY_LIST_MESSAGE


@Composable
 fun EmptySearchResult() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = EMPTY_LIST_MESSAGE,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_disabled)
        )
    }
}
