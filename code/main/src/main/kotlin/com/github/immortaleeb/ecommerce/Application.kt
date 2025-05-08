package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.log4j2.Log4j2Loggers
import com.github.immortaleeb.ecommerce.ordering.PlaceOrder
import com.github.immortaleeb.ecommerce.ordering.strictPositive
import com.github.immortaleeb.ecommerce.shipping.ShipOrder
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

fun main() {
    val loggers = Log4j2Loggers()
    val eventPublisher = object : EventPublisher {
        private val logger = loggers.get("Application")

        override fun publish(event: Event) {
            logger.info("Published event: $event")
        }
    }
    val commandExecutor = DelegatingCommandExecutor(loggers, eventPublisher)

    commandExecutor.execute(PlaceOrder(
        productId = ProductId("1"),
        amount = 10.strictPositive
    ))

    commandExecutor.execute(ShipOrder(
        orderId = OrderId("order-123"),
        productId = ProductId("1")
    ))
}
