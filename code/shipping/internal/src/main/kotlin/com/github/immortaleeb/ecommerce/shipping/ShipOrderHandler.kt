package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers

class ShipOrderHandler(loggers: Loggers, private val eventPublisher: EventPublisher) {
    private val logger = loggers.get(ShipOrderHandler::class)

    fun handle(command: ShipOrder) {
        val event = OrderShipped(orderId = command.orderId)
        eventPublisher.publish(event)
        
        logger.info("Order shipped",
            "orderId" to command.orderId, 
        )
    }
}
