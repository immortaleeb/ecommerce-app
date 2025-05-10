package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class ShipOrderHandler(private val orders: Orders) {
    fun handle(command: ShipOrder) {
        val order = orders.getById(command.orderId)

        order.ship()

        orders.update(order)
    }
}

data class ShippingAddress(val countryCode: String, val city: String, val zipCode: String, val addressLine: String)

interface Orders {
    fun getById(orderId: OrderId): Order
    fun update(order: Order)
}

enum class ShippingStatus {
    NotShipped, Shipped
}
