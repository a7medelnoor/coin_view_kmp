package com.a7medelnoor.coin_view_kmp.domain.usecase

import com.a7medelnoor.coin_view_kmp.domain.model.CryptoDetails
import com.a7medelnoor.coin_view_kmp.domain.repository.CryptoRepository

class GetCryptoDetailsUseCase(
    private val repository: CryptoRepository
) {
    suspend operator fun invoke(id: String): CryptoDetails {
        println("GetCryptoDetailsUseCase invoked with id: $id")
        return repository.getCryptoDetails(id)
    }
} 