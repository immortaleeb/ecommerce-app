package com.github.immortaleeb.ecommerce.foundation.logging.api

interface Logger {
    fun info(message: String, vararg context: Pair<String, Any> = emptyArray())
}