package com.a7medelnoor.coin_view_kmp.presentation.state

import com.a7medelnoor.coin_view_kmp.domain.model.Crypto

data class CryptoListState(
    val cryptos: List<Crypto> = emptyList(),
    val filteredCryptos: List<Crypto> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)