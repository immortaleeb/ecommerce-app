package com.github.immortaleeb.ecommerce.infra.inmemory.ordering

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers
import com.github.immortaleeb.ecommerce.ordering.Order
import com.github.immortaleeb.ecommerce.ordering.OrdersContractTest

class InMemoryOrdersTest : OrdersContractTest {
    private val loggers = TestLoggers()
    private val eventPublisher = FakeEventPublisher()
    override val orders = InMemoryOrders(Order.Factory(loggers, eventPublisher))
}
