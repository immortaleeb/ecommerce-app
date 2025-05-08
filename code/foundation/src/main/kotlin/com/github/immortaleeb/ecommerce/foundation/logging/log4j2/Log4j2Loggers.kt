package com.github.immortaleeb.ecommerce.foundation.logging.log4j2

import kotlin.reflect.KClass
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers as FoundationLoggerFactory
import org.slf4j.LoggerFactory as Slf4jLoggerFactory

class Log4j2Loggers : FoundationLoggerFactory {
    override fun get(name: String) = Log4j2Logger(Slf4jLoggerFactory.getLogger(name))
    override fun get(klass: KClass<*>) = Log4j2Logger(Slf4jLoggerFactory.getLogger(klass.java))
}
