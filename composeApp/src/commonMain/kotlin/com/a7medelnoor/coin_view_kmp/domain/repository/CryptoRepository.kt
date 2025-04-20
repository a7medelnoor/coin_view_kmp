package com.a7medelnoor.coin_view_kmp.domain.repository

import kotlinx.coroutines.flow.Flow
import com.a7medelnoor.coin_view_kmp.domain.model.Crypto
import com.a7medelnoor.coin_view_kmp.domain.model.CryptoDetails
import com.a7medelnoor.coin_view_kmp.util.Resource

interface CryptoRepository {
    fun getCryptocurrencies(): Flow<Resource<List<Crypto>>>
    suspend fun getCryptoDetails(id: String): CryptoDetails
    suspend fun getCryptoList(): List<Crypto>
    suspend fun getCryptoById(id: String): Crypto
}