package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers

class PlaceOrderHandler(loggers: Loggers, private val eventPublisher: EventPublisher) {
    private val logger = loggers.get(PlaceOrderHandler::class)

    fun handle(command: PlaceOrder) {
        eventPublisher.publish(OrderPlaced(productId = command.productId, amount = command.amount))
        logger.info("Order placed", "productId" to command.productId, "amount" to command.amount)
    }
}