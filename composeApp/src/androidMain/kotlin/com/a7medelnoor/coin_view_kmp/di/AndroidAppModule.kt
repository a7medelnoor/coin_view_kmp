package com.a7medelnoor.coin_view_kmp.di

import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module

actual val platformDataModule = module {
    // Android-specific dependencies can be added here
    single<HttpClientEngine>{io.ktor.client.engine.okhttp.OkHttp.create()}
}

