package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class Order private constructor(
    loggers: Loggers,
    private val eventPublisher: EventPublisher,
    val id: OrderId,
    private val shippingAddress: ShippingAddress,
    private var status: ShippingStatus,
) {
    private val logger = loggers.get(Order::class)

    fun ship() {
        if (status == ShippingStatus.Shipped) throw OrderAlreadyShipped(id)

        eventPublisher.publish(OrderShipped(id))
        logger.info("Order shipped",
            "orderId" to id,
            "address" to shippingAddress
        )

        status = ShippingStatus.Shipped
    }

    fun snapshot() = Snapshot(id = id, shippingAddress = shippingAddress, status = status)

    data class Snapshot(val id: OrderId, val shippingAddress: ShippingAddress, val status: ShippingStatus)

    class Factory(private val loggers: Loggers, private val eventPublisher: EventPublisher) {
        fun restore(snapshot: Snapshot) = Order(
            loggers = loggers,
            eventPublisher = eventPublisher,
            id = snapshot.id,
            shippingAddress = snapshot.shippingAddress,
            status = snapshot.status
        )
    }
}

data class ShippingAddress(val countryCode: String, val city: String, val zipCode: String, val addressLine: String)

enum class ShippingStatus {
    NotShipped, Shipped
}
