package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class TestOrders(private val orderFactory: Order.Factory) : Orders {
    private val orders = mutableMapOf<OrderId, Order.Snapshot>()

    override fun getById(orderId: OrderId) = orderFactory.restore(orders[orderId] ?: throw OrderNotFound(orderId))
    override fun update(order: Order) {
        orders.put(order.id, order.snapshot())
    }

    fun assumeExisting(order: Order.Snapshot) = orders.put(order.id, order)
}