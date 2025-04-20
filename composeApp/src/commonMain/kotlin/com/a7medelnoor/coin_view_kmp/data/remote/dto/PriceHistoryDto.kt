package com.a7medelnoor.coin_view_kmp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceHistoryDto(
    val priceUsd: String,
    val time: Long,
    val date: String
)