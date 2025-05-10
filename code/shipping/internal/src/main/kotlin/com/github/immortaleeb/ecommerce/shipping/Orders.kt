package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

interface Orders {
    fun getById(orderId: OrderId): Order
    fun update(order: Order)
}
