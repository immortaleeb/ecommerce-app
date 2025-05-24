package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId
import java.util.concurrent.ConcurrentHashMap

class TestOrders(private val orderFactory: Order.Factory) : Orders {
    private val orders = ConcurrentHashMap<OrderId, Order.Snapshot>()

    override fun getById(orderId: OrderId): Order = orderFactory.restore(orders[orderId] ?: throw OrderNotFound(orderId))
    
    override fun create(
        orderId: OrderId,
        productId: ProductId,
        amount: StrictPositiveInt
    ) {
        orderFactory.create(orderId, productId, amount).also { orders[it.id] = it.snapshot() }
    }
}
