package com.a7medelnoor.coin_view_kmp.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.a7medelnoor.coin_view_kmp.domain.usecase.GetCryptocurrenciesUseCase
import com.a7medelnoor.coin_view_kmp.presentation.state.CryptoListState
import com.a7medelnoor.coin_view_kmp.util.Resource
import com.a7medelnoor.coin_view_kmp.util.StringResources.ERROR_UNEXPECTED


class CryptoListViewModel(
    private val getCryptocurrenciesUseCase: GetCryptocurrenciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CryptoListState())
    val state: StateFlow<CryptoListState> = _state

    private var fetchJob: Job? = null
    private var searchJob: Job? = null

    init {
        getCryptocurrencies()
    }

    fun onSearchQueryChange(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.value = _state.value.copy(searchQuery = query)
            delay(300) // Debounce for 300ms
            filterCryptos()
        }
    }

    private fun filterCryptos() {
        val query = _state.value.searchQuery.trim().lowercase()
        val filteredList = if (query.isEmpty()) {
            _state.value.cryptos
        } else {
            _state.value.cryptos.filter { crypto ->
                crypto.name.lowercase().contains(query) ||
                crypto.symbol.lowercase().contains(query)
            }
        }
        _state.value = _state.value.copy(filteredCryptos = filteredList)
    }

    fun getCryptocurrencies() {
        fetchJob?.cancel()
        fetchJob = getCryptocurrenciesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CryptoListState(
                        cryptos = result.data ?: emptyList(),
                        filteredCryptos = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = CryptoListState(
                        error = result.message ?: ERROR_UNEXPECTED
                    )
                }
                is Resource.Loading -> {
                    _state.value = CryptoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
        searchJob?.cancel()
    }
}
