package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

data class ShipOrder(val orderId: OrderId) : ShippingCommand
