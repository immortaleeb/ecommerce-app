package com.github.immortaleeb.ecommerce.foundation.logging.log4j2

import org.slf4j.Logger
import com.github.immortaleeb.ecommerce.foundation.logging.api.Logger as FoundationLogger

class Log4j2Logger(private val logger: Logger) : FoundationLogger {
    override fun info(message: String, vararg context: Pair<String, Any>) {
        val logBuilder = context.fold(logger.atInfo()) { builder, (k, v) -> builder.addKeyValue(k, v) }
        logBuilder.log(message)
    }
}