package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.logging.log4j2.Log4j2Loggers

fun main() {
    val loggers = Log4j2Loggers()
    val logger = loggers.get("Application")

    logger.info("Hello world", "foo" to "bar", "bar" to "baz")
}
