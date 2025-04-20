package com.a7medelnoor.coin_view_kmp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.a7medelnoor.coin_view_kmp.domain.usecase.GetCryptoDetailsUseCase
import com.a7medelnoor.coin_view_kmp.presentation.state.CryptoDetailsState

class CryptoDetailsViewModel(
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase,
    private val cryptoId: String
) : ViewModel() {
    private val _state = MutableStateFlow(CryptoDetailsState())
    val state = _state.asStateFlow()

    init {
        println("New CryptoDetailsViewModel instance created for: $cryptoId")
        loadCryptoDetails()
    }

    fun loadCryptoDetails() {
        viewModelScope.launch {
            println("Starting to load details for crypto: $cryptoId")
            _state.value = _state.value.copy(isLoading = true)
            try {
                val details = getCryptoDetailsUseCase(cryptoId)
                println("Successfully loaded details for ${details.name} (${details.id})")
                _state.value = CryptoDetailsState(
                    crypto = details,
                    priceHistory = details.priceHistory
                )
            } catch (e: Exception) {
                println("Error loading crypto details for $cryptoId: ${e.message}")
                println("Error type: ${e::class.simpleName}")
                println("Full error stack trace:")
                e.printStackTrace()

                // For serialization errors, try to get more specific info
                if (e.message?.contains("serialization") == true ||
                    e.message?.contains("parsing") == true ||
                    e::class.simpleName?.contains("Serial") == true) {
                    println("Serialization error detected. Raw response may not match expected structure.")
                }

                _state.value = CryptoDetailsState(error = "Error: ${e::class.simpleName} - ${e.message}")
            }
        }
    }
} 