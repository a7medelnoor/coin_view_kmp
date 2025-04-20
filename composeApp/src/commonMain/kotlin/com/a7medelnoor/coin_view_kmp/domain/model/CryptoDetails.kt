package com.a7medelnoor.coin_view_kmp.domain.model

data class CryptoDetails(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val supply: Double,
    val maxSupply: Double?,
    val marketCap: Double,
    val volume24h: Double,
    val price: Double,
    val priceChange24h: Double,
    val vwap24h: Double,
    val priceHistory: List<PricePoint>,
    val markets: List<Market>
)

data class PricePoint(
    val price: Double,
    val timestamp: Long
)

data class Market(
    val exchange: String,
    val pair: String,
    val price: Double,
    val volume24h: Double,
    val volumePercentage: Double
) 