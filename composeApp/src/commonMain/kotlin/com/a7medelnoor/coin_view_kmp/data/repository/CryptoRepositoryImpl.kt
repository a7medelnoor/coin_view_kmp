package com.a7medelnoor.coin_view_kmp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.a7medelnoor.coin_view_kmp.data.mapper.toDomain
import com.a7medelnoor.coin_view_kmp.data.remote.api.CryptoApi
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoDetailsDto
import com.a7medelnoor.coin_view_kmp.domain.model.*
import com.a7medelnoor.coin_view_kmp.domain.repository.CryptoRepository
import com.a7medelnoor.coin_view_kmp.util.Resource

class CryptoRepositoryImpl(
    private val api: CryptoApi
) : CryptoRepository {
    override fun getCryptocurrencies(): Flow<Resource<List<Crypto>>> = flow {
        try {
            emit(Resource.Loading())
            // Use the simplified method instead
            val cryptos = api.getSimplifiedCryptocurrencies().map { it.toDomain() }
            emit(Resource.Success(cryptos))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getCryptoDetails(id: String): CryptoDetails {
        println("Repository getting details for id: $id")
        try {
            val details = api.getCryptoDetails(id).data
            val history = api.getCryptoHistory(id).data
            val markets = api.getCryptoMarkets(id).data

            return CryptoDetails(
                id = details.id,
                rank = details.rank.toIntOrNull() ?: 0,
                symbol = details.symbol,
                name = details.name,
                supply = details.supply.toDoubleOrNull() ?: 0.0,
                maxSupply = details.maxSupply?.toDoubleOrNull(),
                marketCap = details.marketCapUsd.toDoubleOrNull() ?: 0.0,
                volume24h = details.volumeUsd24Hr.toDoubleOrNull() ?: 0.0,
                price = details.priceUsd.toDoubleOrNull() ?: 0.0,
                priceChange24h = details.changePercent24Hr.toDoubleOrNull() ?: 0.0,
                vwap24h = details.vwap24Hr?.toDoubleOrNull() ?: 0.0,
                priceHistory = history.map { dto ->
                    PricePoint(
                        price = dto.priceUsd.toDoubleOrNull() ?: 0.0,
                        timestamp = dto.time
                    )
                },
                markets = markets.map { dto ->
                    Market(
                        exchange = dto.exchangeId,
                        pair = "${dto.baseSymbol}/${dto.quoteSymbol}",
                        price = dto.priceUsd.toDoubleOrNull() ?: 0.0,
                        volume24h = dto.volumeUsd24Hr.toDoubleOrNull() ?: 0.0,
                        volumePercentage = dto.volumePercent.toDoubleOrNull() ?: 0.0
                    )
                }
            )
        } catch (e: Exception) {
            println("Error in repository: ${e.message}")
            throw e
        }
    }

    override suspend fun getCryptoList(): List<Crypto> {
        // Using the existing getCryptocurrencies method from the API
        return api.getCryptocurrencies().data.map { it.toDomain() }
    }

    override suspend fun getCryptoById(id: String): Crypto {
        // The CryptoDetailsResponse has a single data object, not a list
        return api.getCryptoDetails(id).data.toDomain()
    }
}