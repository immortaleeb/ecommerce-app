package com.github.immortaleeb.ecommerce.shipping

class ProvideShippingAddressHandler(
    private val orders: Orders,
) {
    fun handle(command: ProvideShippingAddress) {
        orders.create(command.orderId, command.shippingAddress)
    }
}
