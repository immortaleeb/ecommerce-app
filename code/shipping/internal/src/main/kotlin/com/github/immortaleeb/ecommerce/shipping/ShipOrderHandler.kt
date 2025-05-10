package com.github.immortaleeb.ecommerce.shipping

class ShipOrderHandler(private val orders: Orders) {
    fun handle(command: ShipOrder) {
        val order = orders.getById(command.orderId)

        order.ship()

        orders.update(order)
    }
}

