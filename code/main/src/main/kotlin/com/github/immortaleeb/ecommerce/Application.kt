package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.log4j2.Log4j2Loggers
import com.github.immortaleeb.ecommerce.ordering.OrderingCommandExecutor
import com.github.immortaleeb.ecommerce.ordering.PlaceOrder
import com.github.immortaleeb.ecommerce.ordering.ProductId
import com.github.immortaleeb.ecommerce.ordering.strictPositive

fun main() {
    val loggers = Log4j2Loggers()
    val eventPublisher = object : EventPublisher {
        private val logger = loggers.get("Application")

        override fun publish(event: Event) {
            logger.info("Published event: $event")
        }

    }

    val commandExecutor = OrderingCommandExecutor(loggers, eventPublisher)

    commandExecutor.execute(PlaceOrder(
        productId = ProductId("1"),
        amount = 10.strictPositive
    ))
}
