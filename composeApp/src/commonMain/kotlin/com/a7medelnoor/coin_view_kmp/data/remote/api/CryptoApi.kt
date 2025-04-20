package com.a7medelnoor.coin_view_kmp.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoDetailsResponse
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoDto
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoHistoryResponse
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoMarketsResponse
import com.a7medelnoor.coin_view_kmp.data.remote.dto.CryptoResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class CryptoApi(
    private val client: HttpClient
) {
    suspend fun getCryptoDetails(id: String): CryptoDetailsResponse {
        println("API call for crypto details with id: $id")
        return client.get("v3/assets/$id").body()
    }
    suspend fun getRawCryptocurrencies(): String {
        val response = client.get("v3/assets")
        return response.bodyAsText()
    }
    suspend fun getCryptocurrencies(): CryptoResponse {
        try {
            val response = client.get("v3/assets")
            val responseText = response.bodyAsText()
            println("RAW API RESPONSE LENGTH: ${responseText.length}")
            println("RAW API RESPONSE FIRST 500 CHARS: ${responseText.take(500)}")
            println("RAW API RESPONSE STRUCTURE: Starts with ${responseText.trim().take(10)}, ends with ${responseText.trim().takeLast(10)}")
            return response.body()
        } catch (e: Exception) {
            println("API ERROR TYPE: ${e::class.simpleName}")
            println("API ERROR MESSAGE: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    // Add this to your CryptoApi class
    suspend fun getSimplifiedCryptocurrencies(): List<CryptoDto> {
        val response = client.get("v3/assets")
        val responseText = response.bodyAsText()
        println("SIMPLIFIED API RESPONSE: ${responseText.take(500)}")

        // Use a more lenient JSON parser
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }

        // Try different response formats
        return try {
            // If it's a direct list
            json.decodeFromString<List<CryptoDto>>(responseText)
        } catch (e: Exception) {
            try {
                // If it has a data wrapper
                json.decodeFromString<CryptoResponse>(responseText).data
            } catch (e2: Exception) {
                // If all else fails, return an empty list
                println("Failed to parse response: ${e2.message}")
                emptyList()
            }
        }
    }
    suspend fun getCryptoHistory(id: String): CryptoHistoryResponse {
        println("API call for crypto history with id: $id")
        return client.get("v3/assets/$id/history?interval=d1").body()
    }

    suspend fun getCryptoMarkets(id: String): CryptoMarketsResponse {
        println("API call for crypto markets with id: $id")
        return client.get("v3/assets/$id/markets").body()
    }
}