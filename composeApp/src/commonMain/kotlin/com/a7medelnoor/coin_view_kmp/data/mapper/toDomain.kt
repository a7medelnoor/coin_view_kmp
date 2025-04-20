package com.a7medelnoor.coin_view_kmp.data.mapper

import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoDetailsDto
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoDto
import com.a7medelnoor.coin_view_kmp.domain.model.Crypto

fun CryptoDto.toDomain(): Crypto {
    return Crypto(
        id = id,
        rank = rank.toIntOrNull() ?: 0,
        symbol = symbol,
        name = name,
        price = priceUsd.toDoubleOrNull() ?: 0.0,
        changePercent24h = changePercent24Hr.toDoubleOrNull() ?: 0.0,
        marketCap = marketCapUsd.toDoubleOrNull() ?: 0.0,
        volume24h = volumeUsd24Hr.toDoubleOrNull() ?: 0.0
    )
}

fun CryptoDetailsDto.toDomain(): Crypto {
    return Crypto(
        id = id,
        rank = rank.toIntOrNull() ?: 0,
        symbol = symbol,
        name = name,
        price = priceUsd.toDoubleOrNull() ?: 0.0,
        changePercent24h = changePercent24Hr.toDoubleOrNull() ?: 0.0,
        marketCap = marketCapUsd.toDoubleOrNull() ?: 0.0,
        volume24h = volumeUsd24Hr.toDoubleOrNull() ?: 0.0
    )
}