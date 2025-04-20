package com.a7medelnoor.coin_view_kmp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.CryptoList
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.EmptySearchResult
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.EmptyView
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.ErrorView
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.LoadingIndicator
import com.a7medelnoor.coin_view_kmp.presentation.screens.composables.SearchBar
import com.a7medelnoor.coin_view_kmp.presentation.viewmodel.CryptoListViewModel
import com.a7medelnoor.coin_view_kmp.util.Dimensions
import com.a7medelnoor.coin_view_kmp.util.StringResources.APP_SUBTITLE
import com.a7medelnoor.coin_view_kmp.util.StringResources.APP_TITLE
import com.a7medelnoor.coin_view_kmp.util.StringResources.ERROR_UNEXPECTED

import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CryptoListScreen(
    onNavigateToDetails: (String) -> Unit,
    viewModel: CryptoListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        topBar = {
            Surface(
                elevation = Dimensions.spacing_4,
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top =  Dimensions.spacing_16, bottom =  Dimensions.spacing_8)
                ) {
                    // Title and Market Stats
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimensions.spacing_16)
                    ) {
                        Text(
                            text = APP_TITLE,
                            style = MaterialTheme.typography.h5.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        
                        Spacer(modifier = Modifier.height(Dimensions.spacing_4))
                        
                        Text(
                            text = APP_SUBTITLE,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha =Dimensions.alpha_disabled)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(Dimensions.spacing_16))
                    
                    // Search Bar
                    SearchBar(
                        query = state.searchQuery,
                        onQueryChange = viewModel::onSearchQueryChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimensions.spacing_16)
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    LoadingIndicator()
                }
                state.error != null -> {
                    ErrorView(
                        message = state.error ?: ERROR_UNEXPECTED,
                        onRetry = viewModel::getCryptocurrencies
                    )
                }
                state.filteredCryptos.isNotEmpty() -> {
                    CryptoList(
                        cryptos = state.filteredCryptos,
                        onItemClick = onNavigateToDetails,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                state.searchQuery.isNotEmpty() -> {
                    EmptySearchResult()
                }
                else -> {
                    EmptyView(
                        onRetry = viewModel::getCryptocurrencies
                    )
                }
            }
        }
    }
}






