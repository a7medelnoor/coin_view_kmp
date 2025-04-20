package com.a7medelnoor.coin_view_kmp.presentation.state

import com.a7medelnoor.coin_view_kmp.domain.model.CryptoDetails
import com.a7medelnoor.coin_view_kmp.domain.model.PricePoint

data class CryptoDetailsState(
    val crypto: CryptoDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val priceHistory: List<PricePoint> = emptyList()
)