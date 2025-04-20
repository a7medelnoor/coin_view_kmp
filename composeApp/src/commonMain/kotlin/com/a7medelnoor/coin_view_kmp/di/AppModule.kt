package com.a7medelnoor.coin_view_kmp.di

import com.a7medelnoor.coin_view_kmp.data.repository.CryptoRepositoryImpl
import com.a7medelnoor.coin_view_kmp.domain.repository.CryptoRepository
import com.a7medelnoor.coin_view_kmp.domain.usecase.GetCryptoDetailsUseCase
import com.a7medelnoor.coin_view_kmp.domain.usecase.GetCryptocurrenciesUseCase
import com.a7medelnoor.coin_view_kmp.presentation.viewmodel.CryptoDetailsViewModel
import com.a7medelnoor.coin_view_kmp.presentation.viewmodel.CryptoListViewModel
import com.a7medelnoor.coin_view_kmp.data.remote.api.CryptoApi
import com.a7medelnoor.coin_view_kmp.data.remote.api.HttpClientFactory
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Network module configuration for HTTP client
 */
private val networkModule = module {
    single {
        HttpClientFactory().create()
    }

    single {
        CryptoApi(get())
    }
}

/**
 * Repository module for dependency injection
 */
private val repositoryModule = module {
    single<CryptoRepository> {
        CryptoRepositoryImpl(
            api = get()
        )
    }
}

/**
 * Use case module for business logic
 */
private val useCaseModule = module {
    single {
        GetCryptocurrenciesUseCase(get())
    }
    
    factory {
        GetCryptoDetailsUseCase(get())
    }
}

/**
 * ViewModel module for UI layer
 */
private val viewModelModule = module {
    viewModelOf(::CryptoListViewModel)
    viewModel { parameters ->
        CryptoDetailsViewModel(
            getCryptoDetailsUseCase = get(),
            cryptoId = parameters.get()
        )
    }
}

/**
 * Combined list of all modules
 */
val appModule = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)