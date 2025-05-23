package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

object OrderMother {
    fun unshippedOrderToAtomium(): Order.Snapshot = Order.Snapshot(
        id = orderToAtomium,
        status = ShippingStatus.NotShipped,
        shippingAddress = atomiumShippingAddress()
    )

    fun shippedOrderToAtomium(): Order.Snapshot = Order.Snapshot(
        id = orderToAtomium,
        status = ShippingStatus.Shipped,
        shippingAddress = atomiumShippingAddress()
    )

    private val orderToAtomium: OrderId = OrderId.Companion.of("order_01jtx2t0q3ev0b8tx72s3t00sy")

    fun atomiumShippingAddress(): ShippingAddress = ShippingAddress(
        countryCode = "BE",
        city = "Brussel",
        zipCode = "1020",
        addressLine = "Atomiumplein 1"
    )
}