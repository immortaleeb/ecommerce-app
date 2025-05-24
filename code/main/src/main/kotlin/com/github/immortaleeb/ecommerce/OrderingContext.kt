package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.infra.inmemory.ordering.InMemoryOrders
import com.github.immortaleeb.ecommerce.ordering.Order
import com.github.immortaleeb.ecommerce.ordering.OrderingCommandExecutor

class OrderingContext(loggers: Loggers, eventPublisher: EventPublisher) {
    val orders = InMemoryOrders(Order.Factory(loggers, eventPublisher))
    val commandExecutor = OrderingCommandExecutor(loggers, eventPublisher, orders)
}