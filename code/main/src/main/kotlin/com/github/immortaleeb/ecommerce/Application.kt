package com.github.immortaleeb.ecommerce

import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger("Application")

fun main() {
    logger.atInfo()
        .addKeyValue("foo", "bar")
        .log("Hello World")
}
