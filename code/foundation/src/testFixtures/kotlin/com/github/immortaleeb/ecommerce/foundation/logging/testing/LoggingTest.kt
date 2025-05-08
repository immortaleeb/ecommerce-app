package com.github.immortaleeb.ecommerce.foundation.logging.testing

import org.junit.jupiter.api.AfterEach

interface LoggingTest {
    val loggers: TestLoggers
    val loggedLines get() = loggers.loggedLines

    @AfterEach
    fun resetLogging() {
        loggers.reset()
    }
}
