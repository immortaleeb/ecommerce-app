package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

data class ProvideShippingAddress(
    val orderId: OrderId,
    val shippingAddress: ShippingAddress,
) : ShippingCommand
