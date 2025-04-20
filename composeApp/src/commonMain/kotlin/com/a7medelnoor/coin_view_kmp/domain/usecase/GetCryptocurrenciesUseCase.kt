package com.a7medelnoor.coin_view_kmp.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.a7medelnoor.coin_view_kmp.domain.model.Crypto
import com.a7medelnoor.coin_view_kmp.domain.repository.CryptoRepository
import com.a7medelnoor.coin_view_kmp.util.Resource

class GetCryptocurrenciesUseCase(
    private val repository: CryptoRepository
) {
    operator fun invoke(): Flow<Resource<List<Crypto>>> =
        repository.getCryptocurrencies()
}