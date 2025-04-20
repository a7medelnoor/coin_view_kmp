package com.a7medelnoor.coin_view_kmp.data.remote.dto

import com.a7medelnoor.coin_view_kmp.util.Constants.IMAGE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoDto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String?, // Make this nullable
    val explorer: String?
) {
    val imageUrl: String
        get() = "$IMAGE_URL${symbol.lowercase()}@2x.png"
}