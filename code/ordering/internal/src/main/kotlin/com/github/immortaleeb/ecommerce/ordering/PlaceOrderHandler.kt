package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class PlaceOrderHandler(
    loggers: Loggers,
    private val eventPublisher: EventPublisher,
    val generateOrderId: () -> OrderId = { OrderId.generate() }
) {
    private val logger = loggers.get(PlaceOrderHandler::class)

    fun handle(command: PlaceOrder) {
        eventPublisher.publish(OrderPlaced(orderId = generateOrderId(), productId = command.productId, amount = command.amount))
        logger.info("Order placed", "orderId" to generateOrderId, "productId" to command.productId, "amount" to command.amount)
    }
}