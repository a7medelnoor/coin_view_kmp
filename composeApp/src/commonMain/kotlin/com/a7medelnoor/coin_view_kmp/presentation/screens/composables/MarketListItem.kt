package com.a7medelnoor.coin_view_kmp.presentation.screens.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.a7medelnoor.coin_view_kmp.domain.model.Market
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter
import com.a7medelnoor.coin_view_kmp.util.StringResources.LABEL_VOLUME_SHORT

@Composable
 fun MarketListItem(market: Market) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.spacing_4),
        elevation = Dimensions.spacing_1
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing_16),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = market.exchange,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = market.pair,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_disabled)
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = NumberFormatter.formatPrice(market.price),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "$LABEL_VOLUME_SHORT ${NumberFormatter.formatPrice(market.volume24h)}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_disabled)
                )
            }
        }
    }
}
