package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class PlaceOrderHandler(
    loggers: Loggers,
    private val eventPublisher: EventPublisher,
    private val orders: Orders,
) {
    private val logger = loggers.get(PlaceOrderHandler::class)

    fun handle(command: PlaceOrder) {
        orders.create(command.orderId, command.productId, command.amount)
        eventPublisher.publish(OrderPlaced(orderId = command.orderId, productId = command.productId, amount = command.amount))
        logger.info("Order placed", "orderId" to command.orderId, "productId" to command.productId, "amount" to command.amount)
    }
}