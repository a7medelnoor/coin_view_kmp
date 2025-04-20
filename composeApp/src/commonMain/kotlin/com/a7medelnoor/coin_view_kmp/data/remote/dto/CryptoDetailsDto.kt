package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoDetailsDto(
    val id: String,
    val rank: String,  // Assuming this is a String based on API docs
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String?,
    val explorer: String?
)

@Serializable
data class PricePoint(
    val price: Double,
    val timestamp: Long
)

@Serializable
data class Market(
    val exchange: String,
    val pair: String,
    val price: Double,
    val volume24h: Double,
    val volumePercentage: Double
)
@Serializable
data class CryptoResponseArray(
    val cryptos: List<CryptoDto> = emptyList()
)