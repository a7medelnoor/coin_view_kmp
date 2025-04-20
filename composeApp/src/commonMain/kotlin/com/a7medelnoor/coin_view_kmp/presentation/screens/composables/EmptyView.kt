package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.StringResources.BUTTON_REFRESH
import com.a7medelnoor.coin_view_kmp.util.StringResources.EMPTY_LIST_MESSAGE

@Composable
fun EmptyView(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(Dimensions.spacing_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing_8)
    ) {
        Text(
            text = EMPTY_LIST_MESSAGE,
            style = MaterialTheme.typography.body1
        )
        Button(onClick = onRetry) {
            Text(BUTTON_REFRESH)
        }
    }
}
