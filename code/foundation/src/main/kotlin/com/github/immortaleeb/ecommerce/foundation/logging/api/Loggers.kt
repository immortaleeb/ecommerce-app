package com.github.immortaleeb.ecommerce.foundation.logging.api

import kotlin.reflect.KClass

interface Loggers {
    fun get(name: String): Logger
    fun get(klass: KClass<*>): Logger
}