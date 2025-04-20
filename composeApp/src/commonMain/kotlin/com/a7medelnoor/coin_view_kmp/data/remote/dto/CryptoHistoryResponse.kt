package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoHistoryResponse(
    val data: List<PriceHistoryDto>,
    val timestamp: Long
)