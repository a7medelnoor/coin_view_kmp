package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoMarketsResponse(
    val data: List<MarketDto>,
    val timestamp: Long
)