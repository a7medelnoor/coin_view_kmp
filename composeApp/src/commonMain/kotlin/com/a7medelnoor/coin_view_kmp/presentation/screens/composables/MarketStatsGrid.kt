package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.a7medelnoor.coin_view_kmp.domain.model.CryptoDetails
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter
import com.a7medelnoor.coin_view_kmp.util.StringResources.INFINITY_SYMBOL
import com.a7medelnoor.coin_view_kmp.util.StringResources.LABEL_MARKET_CAP
import com.a7medelnoor.coin_view_kmp.util.StringResources.LABEL_MAX_SUPPLY
import com.a7medelnoor.coin_view_kmp.util.StringResources.LABEL_SUPPLY
import com.a7medelnoor.coin_view_kmp.util.StringResources.LABEL_VOLUME

@Composable
 fun MarketStatsGrid(crypto: CryptoDetails) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing_8)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing_8)
        ) {
            StatCard(
                title = LABEL_MARKET_CAP,
                value = NumberFormatter.formatPrice(crypto.marketCap),
                modifier = Modifier.weight(Dimensions.alpha_large_1)
            )
            StatCard(
                title = LABEL_VOLUME,
                value = NumberFormatter.formatPrice(crypto.volume24h),
                modifier = Modifier.weight(Dimensions.alpha_large_1)
            )
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing_8)
        ) {
            StatCard(
                title = LABEL_SUPPLY,
                value = NumberFormatter.formatPrice(crypto.supply),
                modifier = Modifier.weight(Dimensions.alpha_large_1)
            )
            StatCard(
                title = LABEL_MAX_SUPPLY,
                value = crypto.maxSupply?.let { NumberFormatter.formatPrice(it) } ?: INFINITY_SYMBOL,
                modifier = Modifier.weight(Dimensions.alpha_large_1)
            )
        }
    }
}