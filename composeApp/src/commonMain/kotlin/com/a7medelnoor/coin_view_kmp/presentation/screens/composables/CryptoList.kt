package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.a7medelnoor.coin_view_kmp.domain.model.Crypto
import com.a7medelnoor.coin_view_kmp.util.Dimensions

@Composable
fun CryptoList(
    cryptos: List<Crypto>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimensions.spacing_16),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing_8)
    ) {
        items(cryptos) { crypto ->
            CryptoListItem(
                crypto = crypto,
                onItemClick = onItemClick,
            )
        }
    }
}