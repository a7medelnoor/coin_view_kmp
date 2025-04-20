package com.a7medelnoor.coin_view_kmp.util

object StringResources {
    // App info
    const val APP_TITLE = "CoinView"
    const val APP_SUBTITLE = "Live prices of top cryptocurrencies"
    
    // Button text
    const val BUTTON_REFRESH = "Refresh"
    const val BUTTON_RETRY = "Retry"

    // Screen titles
    const val CRYPTO_LIST_TITLE = "Cryptocurrencies"
    const val CRYPTO_DETAILS_TITLE = "Crypto Details"

    // Error messages
    const val ERROR_LOADING_DATA = "Failed to load data"
    const val ERROR_NO_INTERNET = "No internet connection"
    const val ERROR_UNEXPECTED = "An unexpected error occurred"
    
    // Empty states
    const val EMPTY_LIST_MESSAGE = "No cryptocurrencies found"

    // Search
    const val SEARCH_PLACEHOLDER = "Search cryptocurrencies"
    const val SEARCH_ICON_DESCRIPTION = "Search"

    // Content descriptions
    const val BACK_BUTTON_DESCRIPTION = "Navigate back"
    const val PRICE_INCREASED_DESCRIPTION = "Price increased"
    const val PRICE_DECREASED_DESCRIPTION = "Price decreased"

    // Detail screen section titles
    const val SECTION_MARKET_STATS = "Market Stats"
    const val SECTION_PRICE_HISTORY = "Price History"
    const val SECTION_MARKETS = "Markets"

    // Detail screen labels
    const val LABEL_CURRENT_PRICE = "Current Price"
    const val LABEL_MARKET_CAP = "Market Cap"
    const val LABEL_VOLUME = "24h Volume"
    const val LABEL_VOLUME_SHORT = "Vol:"
    const val LABEL_SUPPLY = "Supply"
    const val LABEL_MAX_SUPPLY = "Max Supply"
    const val INFINITY_SYMBOL = "∞"

    // Navigation routes
    object Routes {
        const val CRYPTO_LIST = "crypto_list"
        const val CRYPTO_DETAILS = "crypto_details/{cryptoId}"

        // For navigation without having to concatenate strings
        fun cryptoDetails(cryptoId: String) = "crypto_details/$cryptoId"
    }
}