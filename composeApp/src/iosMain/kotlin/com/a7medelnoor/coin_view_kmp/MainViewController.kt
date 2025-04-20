package com.a7medelnoor.coin_view_kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.a7medelnoor.coin_view_kmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    AppMain()
}