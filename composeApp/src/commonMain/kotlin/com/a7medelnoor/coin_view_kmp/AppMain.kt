
package com.a7medelnoor.coin_view_kmp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.a7medelnoor.coin_view_kmp.presentation.screens.CryptoDetailsScreen
import com.a7medelnoor.coin_view_kmp.presentation.screens.CryptoListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppMain() {
    MaterialTheme {
        var currentCryptoId by remember { mutableStateOf<String?>(null) }

        if (currentCryptoId == null) {
            CryptoListScreen(
                onNavigateToDetails = { cryptoId ->
                    println("Navigation triggered for crypto: $cryptoId")
                    currentCryptoId = cryptoId
                }
            )
        } else {
            println("Showing details screen for crypto: $currentCryptoId")
            CryptoDetailsScreen(
                cryptoId = currentCryptoId!!,
                onNavigateBack = {
                    currentCryptoId = null
                }
            )
        }
    }
} 