package com.a7medelnoor.coin_view_kmp.presentation.screens.composables


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.a7medelnoor.coin_view_kmp.presentation.state.CryptoDetailsState
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter

import com.a7medelnoor.coin_view_kmp.util.StringResources.SECTION_MARKETS
import com.a7medelnoor.coin_view_kmp.util.StringResources.SECTION_MARKET_STATS
import com.a7medelnoor.coin_view_kmp.util.StringResources.SECTION_PRICE_HISTORY

@Composable
fun CryptoDetailsContent(
    state: CryptoDetailsState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(Dimensions.spacing_16),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing_16)
    ) {
        state.crypto?.let { crypto ->
            // Price Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = Dimensions.spacing_2,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(Dimensions.spacing_16),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = NumberFormatter.formatPrice(crypto.price),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.height(Dimensions.spacing_8))
                        
                        PriceChangeIndicator(
                            priceChange = crypto.priceChange24h,
                            modifier = Modifier.padding(vertical = Dimensions.spacing_4)
                        )
                    }
                }
            }

            // Market Stats
            item {
                Text(
                    text = SECTION_MARKET_STATS,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                MarketStatsGrid(crypto)
            }

            // Price History
            item {
                Text(
                    text = SECTION_PRICE_HISTORY,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                PriceHistoryChart(state.priceHistory)
            }

            // Markets
            item {
                Text(
                    text = SECTION_MARKETS,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }

            // Market List
            items(crypto.markets) { market ->
                MarketListItem(market)
            }
        }
    }
}