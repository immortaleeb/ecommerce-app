package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.log4j2.Log4j2Loggers
import com.github.immortaleeb.ecommerce.infra.inmemory.shipping.InMemoryOrders
import com.github.immortaleeb.ecommerce.ordering.PlaceOrder
import com.github.immortaleeb.ecommerce.ordering.strictPositive
import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.shipping.ProvideShippingAddress
import com.github.immortaleeb.ecommerce.shipping.ShipOrder
import com.github.immortaleeb.ecommerce.shipping.ShippingAddress
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
    val orderFactory = Order.Factory(loggers, eventPublisher)
    val orders = InMemoryOrders(orderFactory)

    val commandExecutor = DelegatingCommandExecutor(orders, loggers, eventPublisher)

    val orderId = OrderId.generate()
    commandExecutor.execute(
        ProvideShippingAddress(
            orderId, ShippingAddress(
                countryCode = "BE",
                city = "Brussel",
                zipCode = "1020",
                addressLine = "Atomiumplein 1",
            )
        )
    )

    commandExecutor.execute(
        PlaceOrder(
            productId = ProductId.generate(),
            amount = 10.strictPositive
        )
    )


    commandExecutor.execute(
        ShipOrder(
            orderId = orderId,
        )
    )
}
