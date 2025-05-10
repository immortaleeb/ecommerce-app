package com.github.immortaleeb.ecommerce.foundation.logging.testing

import com.github.immortaleeb.ecommerce.foundation.logging.api.Logger
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import kotlin.reflect.KClass

class TestLoggers : Loggers {
    private var _loggedLines: MutableList<LoggedLine> = mutableListOf()
    val loggedLines: List<LoggedLine> get() = _loggedLines

    override fun get(name: String): Logger = object : Logger {
        override fun info(message: String, vararg context: Pair<String, Any>) {
            _loggedLines.add(LoggedLine(
                message = message,
                context = mapOf(*context)
            ))
        }
    }

    fun reset() {
        _loggedLines.clear()
    }

    override fun get(klass: KClass<*>) = get(klass.java.name)

    data class LoggedLine(val message: String, val context: Map<String, Any>)
}
