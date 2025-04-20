package com.a7medelnoor.coin_view_kmp.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.CryptoDetailsContent
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.ErrorView
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.LoadingIndicator
import com.a7medelnoor.coin_view_kmp.presentation.viewmodel.CryptoDetailsViewModel
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.NumberFormatter
import com.a7medelnoor.coin_view_kmp.util.StringResources.BACK_BUTTON_DESCRIPTION
import com.a7medelnoor.coin_view_kmp.util.StringResources.ERROR_UNEXPECTED
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun CryptoDetailsScreen(
    cryptoId: String,
    onNavigateBack: () -> Unit
) {
    // Create a new key for each cryptoId to force ViewModel recreation
    key(cryptoId) {
        val viewModel: CryptoDetailsViewModel = koinViewModel(key = cryptoId) { 
            parametersOf(cryptoId) 
        }
        
        val state by viewModel.state.collectAsState()

        LaunchedEffect(cryptoId) {
            println("LaunchedEffect triggered for crypto: $cryptoId")
            viewModel.loadCryptoDetails()
        }

        // Add logging when crypto details are loaded
        state.crypto?.let { crypto ->
            println("\nLoaded Crypto Details:")
            println("ID: ${crypto.id}")
            println("Name: ${crypto.name}")
            println("Symbol: ${crypto.symbol}")
            println("Rank: ${crypto.rank}")
            println("Price: ${NumberFormatter.formatPrice(crypto.price)}")
            println("24h Change: ${crypto.priceChange24h}%")
            println("Market Cap: ${NumberFormatter.formatPrice(crypto.marketCap)}")
            println("Volume 24h: ${NumberFormatter.formatPrice(crypto.volume24h)}")
            println("Supply: ${NumberFormatter.formatPrice(crypto.supply)}")
            println("Max Supply: ${crypto.maxSupply?.let { NumberFormatter.formatPrice(it) } ?: "∞"}")
            println("Markets Count: ${crypto.markets.size}")
            println("Price History Points: ${state.priceHistory.size}")
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = Dimensions.elevation_small_0,
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = BACK_BUTTON_DESCRIPTION
                            )
                        }
                    },
                    title = {
                        state.crypto?.let { crypto ->
                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = crypto.name,
                                    style = MaterialTheme.typography.h6.copy(
                                        fontSize = MaterialTheme.typography.h6.fontSize * Dimensions.alpha_medium_09
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = Dimensions.max_line
                                )
                                Text(
                                    text = crypto.symbol.uppercase(),
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = Dimensions.alpha_disabled),
                                    maxLines = Dimensions.max_line
                                )
                            }
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                when {
                    state.isLoading -> LoadingIndicator()

                    state.error != null -> {
                        // Display the error directly in the UI for debugging
                        Column {
                            Text(
                                "DEBUG ERROR INFO:",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.error
                            )
                            Text(
                                state.error ?: ERROR_UNEXPECTED,
                                style = MaterialTheme.typography.body2
                            )
                            // Regular error view
                            ErrorView(
                                message = state.error ?: ERROR_UNEXPECTED,
                                onRetry = viewModel::loadCryptoDetails
                            )
                        }
                    }
                    state.crypto != null -> {
                        CryptoDetailsContent(
                            state = state,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
