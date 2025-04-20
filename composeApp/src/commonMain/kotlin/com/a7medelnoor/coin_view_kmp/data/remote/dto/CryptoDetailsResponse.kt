package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoDetailsResponse(
    val data: CryptoDetailsDto,
    val timestamp: Long
)