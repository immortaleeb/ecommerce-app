package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers

class TestContext {
    val loggers = TestLoggers()
    val eventPublisher = FakeEventPublisher()
    val orderFactory = Order.Factory(loggers, eventPublisher)
    val orders = TestOrders(orderFactory)
    val commandExecutor = ShippingCommandExecutor(orders)

    val publishedEvents get() = eventPublisher.publishedEvents
}
