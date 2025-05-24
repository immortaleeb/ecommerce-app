package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

interface Orders {
    fun getById(orderId: OrderId): Order
    fun create(orderId: OrderId, productId: ProductId, amount: StrictPositiveInt)
}
