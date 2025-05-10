package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

object OrderMother {
    fun unshippedOrderToAtomium(): Order.Snapshot = Order.Snapshot(
        id = OrderId.Companion.of("order_01jtx2t0q3ev0b8tx72s3t00sy"),
        status = ShippingStatus.NotShipped,
        shippingAddress = atomium()
    )

    private fun atomium(): ShippingAddress = ShippingAddress(
        countryCode = "BE",
        city = "Brussel",
        zipCode = "1020",
        addressLine = "Atomiumplein 1"
    )
}
