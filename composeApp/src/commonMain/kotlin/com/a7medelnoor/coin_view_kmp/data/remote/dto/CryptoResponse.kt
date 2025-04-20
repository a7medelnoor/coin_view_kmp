package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoResponse(
    val timestamp: Long? = null,
    val data: List<CryptoDto>
)