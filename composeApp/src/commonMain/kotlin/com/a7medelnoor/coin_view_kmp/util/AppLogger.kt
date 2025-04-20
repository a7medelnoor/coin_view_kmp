package com.a7medelnoor.coin_view_kmp.util

object AppLogger {
    private val logs = mutableListOf<String>()

    fun log(message: String) {
        // Print to console
        println("APP_LOG: $message")

        // Store in memory for later retrieval
        logs.add(message)
    }

    fun getRecentLogs(count: Int = 20): List<String> {
        return logs.takeLast(count)
    }

    fun getAllLogs(): List<String> {
        return logs.toList()
    }

    fun clearLogs() {
        logs.clear()
    }
}