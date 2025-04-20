package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketDto(
    val exchangeId: String,
    val baseId: String,
    val quoteId: String,
    val baseSymbol: String,
    val quoteSymbol: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val volumePercent: String
)