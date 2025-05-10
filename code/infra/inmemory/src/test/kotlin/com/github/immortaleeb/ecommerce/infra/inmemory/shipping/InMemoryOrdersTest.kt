package com.github.immortaleeb.ecommerce.infra.inmemory.shipping

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.LoggingTest
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers
import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.shipping.OrdersContractTest

class InMemoryOrdersTest : OrdersContractTest, LoggingTest {
    override val loggers = TestLoggers()
    override val orderFactory: Order.Factory = Order.Factory(loggers, FakeEventPublisher())
    override val orders = InMemoryOrders(orderFactory)

    fun assumeExisting(order: Order) = orders.assumeExisting(order)
}