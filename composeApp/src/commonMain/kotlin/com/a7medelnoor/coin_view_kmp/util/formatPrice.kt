package com.a7medelnoor.coin_view_kmp.util

import kotlin.math.pow
import kotlin.math.roundToInt

object NumberFormatter {
    /**
     * Formats a price value, adding appropriate suffixes (B for billions, M for millions)
     * and currency symbols ($).
     *
     * @param price The price to format.
     * @return The formatted price string.
     */
    fun formatPrice(price: Double): String {
        return when {
            price >= 1_000_000_000 -> "$${(price / 1_000_000_000).roundTo(2)}B"
            price >= 1_000_000 -> "$${(price / 1_000_000).roundTo(2)}M"
            price >= 1_000 -> "$${price.roundTo(2).withCommas()}"
            else -> "$${price.roundTo(2)}"
        }
    }

    /**
     * Formats a percentage value, adding the percentage symbol (%).
     *
     * @param percentage The percentage to format.
     * @return The formatted percentage string.
     */
    fun formatPercentage(percentage: Double): String {
        return "${percentage.roundTo(2)}%"
    }

    /**
     * Formats a market capitalization value, adding appropriate suffixes (B for billions,
     * M for millions) and currency symbols ($).
     *
     * @param marketCap The market capitalization to format.
     * @return The formatted market capitalization string.
     */
    fun formatMarketCap(marketCap: Double): String {
        return when {
            marketCap >= 1_000_000_000 -> "$${(marketCap / 1_000_000_000).roundTo(2)}B"
            marketCap >= 1_000_000 -> "$${(marketCap / 1_000_000).roundTo(2)}M"
            else -> "$${marketCap.roundTo(2).withCommas()}"
        }
    }

    /**
     * Formats a volume value, adding appropriate suffixes (B for billions, M for millions).
     *
     * @param volume The volume to format.
     * @return The formatted volume string.
     */
    fun formatVolume(volume: Double): String {
        return when {
            volume >= 1_000_000_000 -> "${(volume / 1_000_000_000).roundTo(2)}B"
            volume >= 1_000_000 -> "${(volume / 1_000_000).roundTo(2)}M"
            else -> volume.roundTo(2).withCommas()
        }
    }

    /**
     * Helper function to round a Double to a specified number of decimal places.
     *
     * @param decimals The number of decimal places to round to.
     * @return The rounded Double.
     */
    private fun Double.roundTo(decimals: Int): Double {
        val factor = 10.0.pow(decimals)
        return (this * factor).roundToInt() / factor
    }

    /**
     * Helper function to format a Double with commas as thousands separators.
     *
     * @return The formatted string with commas.
     */
    private fun Double.withCommas(): String {
        return this.roundTo(2).toString().withCommas()
    }
    private fun String.withCommas(): String {
        val parts = this.split(".")
        var integerPart = parts[0]
        val decimalPart = if (parts.size > 1) ".${parts[1]}" else ""

        val formattedIntegerPart = StringBuilder()
        var count = 0
        for (i in integerPart.length - 1 downTo 0) {
            formattedIntegerPart.insert(0, integerPart[i])
            count++
            if (count % 3 == 0 && i != 0) {
                formattedIntegerPart.insert(0, ",")
            }
        }

        return formattedIntegerPart.toString() + decimalPart
    }
}