package com.github.immortaleeb.ecommerce

import com.ecommerce.catalog.api.ListProducts
import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.log4j2.Log4j2Loggers
import com.github.immortaleeb.ecommerce.ordering.PlaceOrder
import com.github.immortaleeb.ecommerce.ordering.strictPositive
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
    
    val ordering = OrderingContext(loggers, eventPublisher)
    val shipping = ShippingContext(loggers, eventPublisher)
    val catalog = CatalogContext()

    val queryExecutor = catalog.queryExecutor
    val commandExecutor = DelegatingCommandExecutor(loggers, ordering, shipping)

    val products = queryExecutor.execute(ListProducts)
    val frankenstein = products.first { it.description == "Frankenstein" }

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
            orderId = orderId,
            productId = frankenstein.id,
            amount = 10.strictPositive
        )
    )

    commandExecutor.execute(
        ShipOrder(
            orderId = orderId,
        )
    )
}
