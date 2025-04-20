package com.a7medelnoor.coin_view_kmp

import android.app.Application
import com.a7medelnoor.coin_view_kmp.di.appModule
import com.a7medelnoor.coin_view_kmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
     initKoin{
                 androidContext(this@App)
                modules(appModule)
         
     }
    }
}