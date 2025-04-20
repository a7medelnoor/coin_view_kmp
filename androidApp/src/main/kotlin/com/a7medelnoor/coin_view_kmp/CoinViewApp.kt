package com.a7medelnoor.coin_view_kmp

import android.app.Application
import com.a7medelnoor.coin_view_kmp.util.ConfigurationProvider

class CoinViewApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ConfigurationProvider.initialize(this)
        // Other initialization code
    }
} 