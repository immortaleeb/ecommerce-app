package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.infra.inmemory.shipping.InMemoryOrders
import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.shipping.ShippingCommandExecutor

class ShippingContext(loggers: Loggers, eventPublisher: EventPublisher) {
    val orders = InMemoryOrders(Order.Factory(loggers, eventPublisher))
    val commandExecutor = ShippingCommandExecutor(orders)
}