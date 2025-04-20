package com.a7medelnoor.coin_view_kmp.domain.model

data class Crypto(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val price: Double,
    val changePercent24h: Double,
    val marketCap: Double,
    val volume24h: Double
) {
    val imageUrl: String
        get() = "https://assets.coincap.io/assets/icons/${symbol.lowercase()}@2x.png"
}